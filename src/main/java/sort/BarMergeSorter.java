package sort;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.*;

/**
 * Creates a bar graph of random values and sorts it using the merge sort.
 *
 * @author Daniel Phan
 * @version 1.10.17
 */
public class BarMergeSorter extends JComponent {
    /**
     * Constructs a new BarMergeSorter with the given bar count and frame dimensions.
     * (Postcondition: frameDims_ and nums_ are initialized)
     * @param barCount the number of bars in the bar graph
     * @param frameDims the dimensions of the JFrame's content pane
     * (Precondition: barCount is positive)
     */
    public BarMergeSorter(int barCount, Dimension frameDims) {
        frameDims_ = frameDims;
        Random rand = new Random();
        nums_ = IntStream.range(0, barCount)
                .map(n -> rand.nextInt(VAL_LIMIT - VAL_MIN) + VAL_MIN)
                .toArray();
        mergedSection_ = null;
    }

    /**
     * Paints the bar graph onto the JFrame
     * (Postcondition: The bar graph is painted onto the JFrame)
     * @param g the graphical utility to draw onto the JFrame
     * (Precondition: createRectangles returns rectangles with positive dimensions)
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (Rectangle r : createRectangles(nums_, 0)) {
            g2.setColor(isSorted_ ? Color.GREEN : new Color(148, 0, 211));
            g2.fill(r);
            g2.setColor(Color.WHITE);
            g2.draw(r);
        }
        if (mergedSection_ != null) { //aka has next state
            Rectangle[] recs = createRectangles(mergedSection_, mergedOffset_);
            for (int i = 0; i <= mergedDrawCount_; i++) {
                g2.setColor(Color.RED);
                g2.fill(recs[i]);
                g2.setColor(Color.WHITE);
                g2.draw(recs[i]);
            }
        }
    }

    /**
     * Runs the merge sort.
     * (Postcondition: The bar graph is sorted.)
     * (Precondition: nums_ is nonnull.)
     */
    public void run() {
        mergeSort(nums_, 0);
    }

    /**
     * Returns the string representation of nums_
     * (Postcondition: The string representation of nums_ is returned)
     * @return the string representation of nums_
     * (Precondition: nums_ is nonnull)
     */
    @Override
    public String toString() {
        return Arrays.toString(nums_);
    }

    private int[] nums_; //the array of numbers to sort
    private int[] mergedSection_; //the section of nums that was just merged
    private int mergedOffset_; //lower index of the merged section in nums_
    private int mergedDrawCount_; //the number of rectangles in mergedSection_ to draw
    private boolean isSorted_; //whether or not the bar graph is sorted
    private Dimension frameDims_; //the dimensions of the JFrame
    private static final int VAL_MIN = 5; //the inclusive lower bound of the random values
    private static final int VAL_LIMIT = 100; //the exclusive upper bound of the random values
    private static final int DRAW_DELAY = 50; //the drawing delay in ms

    /**
     * Sorts an array using the merge sort.
     * (Postcondition: A sorted array is returned.)
     * @param nums the array to sort
     * @param startIndex the index of nums[0] in nums_
     * @return the sorted version of nums
     * (Precondition: nums is nonnull)
     */
    private int[] mergeSort(int[] nums, int startIndex) {
        if (nums.length == 1 || nums.length == 0) {
            return nums;
        }

        int[] a1 = new int[nums.length / 2];
        int[] a2 = new int[(nums.length + 1) / 2];

        System.arraycopy(nums, 0, a1, 0, a1.length);
        System.arraycopy(nums, a1.length, a2, 0, a2.length);

        int[] result = merge(mergeSort(a1, startIndex), mergeSort(a2, startIndex + a1.length), startIndex);

        if (nums.length == nums_.length) { //the first call
            mergedSection_ = null;
            isSorted_ = true;
            repaint();
        }

        return result;
    }

    /**
     * Helps mergeSort by performing the merge part of the merge sort
     * (Postcondition: refs1 and refs2 are merged.)
     * @param nums1 the first array to merge
     * @param nums2 the second array to merge
     * @param startIndex the index of nums1[0] in nums_
     * @return the merged array
     * (Precondition: refs1 and refs2 are both nonnull)
     */
    private int[] merge(int[] nums1, int[] nums2, int startIndex) {
        mergedSection_ = new int[nums1.length + nums2.length];

        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            mergedSection_[k++] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
        }
        while (i < nums1.length) {
            mergedSection_[k++] = nums1[i++];
        }
        while (j < nums2.length) {
            mergedSection_[k++] = nums2[j++];
        }

        mergedOffset_ = startIndex;

        for (i = 0; i < mergedSection_.length; i++) {
            mergedDrawCount_ = i; //fuck paintComponent being async
            repaint();
            try {
                Thread.sleep(DRAW_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.arraycopy(mergedSection_, 0, nums_, startIndex, mergedSection_.length);

        return mergedSection_;
    }

    /**
     * Creates rectangles based on nums_.
     * (Postcondition: An array of rectangles based on nums is returned.)
     * @param nums the array of values
     * @param offset the relative offset of the rectangles
     * @return an array of rectangles based on nums
     * (Precondition: nums is nonnull and offset is nonnegative)
     */
    private Rectangle[] createRectangles(int[] nums, int offset) {
        Rectangle[] recs = new Rectangle[nums.length];
        int barW = frameDims_.height / nums_.length;
        double scale = frameDims_.width * 1.0 / VAL_LIMIT;
        for (int i = 0; i < nums.length; i++) {
            int barL = (int) (scale * nums[i]);
            recs[i] = new Rectangle(0, barW * (i + offset), barL, barW);
        }
        return recs;
    }
}
