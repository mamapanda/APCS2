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
        previousState_ = null;
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
        for (Rectangle r : createRectangles(previousState_ == null ? nums_ : previousState_)) {
            g2.setColor(new Color(148, 0, 211)); //previous is null when added to frame
            g2.fill(r);
            g2.setColor(Color.WHITE);
            g2.draw(r);
        }
        if (previousState_ != null) { //aka has next state
            Rectangle[] recs = createRectangles(nums_);
            for (int i = i1_; i <= i2_; i++) {
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

    private int[] previousState_; //the previous state of nums
    private int[] nums_; //the array of numbers to sort
    private int i1_; //lower index of the merged section to draw
    private int i2_; //upper index of the merged section to draw
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
            try {
                Thread.sleep(DRAW_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            previousState_ = null;
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
        if (previousState_ == null) {
            previousState_ = new int[nums_.length];
        }
        System.arraycopy(nums_, 0, previousState_, 0, nums_.length);

        int[] result = new int[nums1.length + nums2.length];

        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            result[k++] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
        }
        while (i < nums1.length) {
            result[k++] = nums1[i++];
        }
        while (j < nums2.length) {
            result[k++] = nums2[j++];
        }

        i1_ = startIndex;
        int stopIndex = startIndex + result.length - 1;

        for (i = startIndex; i <= stopIndex; i++) {
            nums_[i] = result[i - startIndex];
        }

        for (i = i1_; i <= stopIndex; i++) {
            i2_ = i; //fuck paintComponent being async
            try {
                Thread.sleep(DRAW_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }

        return result;
    }

    /**
     * Creates rectangles based on nums_.
     * (Postcondition: An array of rectangles based on nums_ is returned.)
     * @return an array of rectangles based on nums_
     * (Precondition: nums is nonnull)
     */
    private Rectangle[] createRectangles(int[] nums) {
        Rectangle[] recs = new Rectangle[nums.length];
        int barW = (int) (frameDims_.height * 1.0 / nums.length);
        double scale = frameDims_.width * 1.0 / VAL_LIMIT;
        for (int i = 0; i < nums.length; i++) {
            int barL = (int) (scale * nums[i]);
            recs[i] = new Rectangle(0, barW * i, barL, barW);
        }
        return recs;
    }
}
