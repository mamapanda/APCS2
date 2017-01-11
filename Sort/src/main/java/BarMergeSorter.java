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
                .mapToObj(Int::valueOf)
                .toArray(Int[]::new);
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
        for (Rectangle r : createRectangles()) {
            g2.setColor(new Color(148, 0, 211));
            g2.fill(r);
            g2.setColor(Color.WHITE);
            g2.draw(r);
        }
    }

    /**
     * Runs the merge sort.
     * (Postcondition: The bar graph is sorted.)
     * (Precondition: nums_ is nonnull.)
     */
    public void run() {
        mergeSort(nums_);
    }

    /**
     * Returns the string representation of nums_
     * (Postcondition: The string representation of nums_ is returned)
     * @return the string representation of nums_
     * (Precondition: nums_ is nonnull)
     */
    @Override
    public String toString() {
        return Arrays.deepToString(nums_);
    }

    private Int[] nums_; //the array of numbers to sort
    private Dimension frameDims_; //the dimensions of the JFrame
    private static final int VAL_MIN = 5; //the inclusive lower bound of the random values
    private static final int VAL_LIMIT = 100; //the exclusive upper bound of the random values
    private static final int DRAW_DELAY = 500; //the drawing delay in ms

    /**
     * Sorts an array using the merge sort.
     * (Postcondition: A sorted array is returned.)
     * @param nums the array to sort
     * @return the sorted version of nums
     * (Precondition: nums is nonnull)
     */
    private Int[] mergeSort(Int[] nums) {
        if (nums.length == 1 || nums.length == 0) {
            return nums;
        }

        Int[] a1 = new Int[nums.length / 2];
        Int[] a2 = new Int[(nums.length + 1) / 2];

        System.arraycopy(nums, 0, a1, 0, a1.length);
        System.arraycopy(nums, a1.length, a2, 0, a2.length);

        return merge(mergeSort(a1), mergeSort(a2));
    }

    /**
     * Helps mergeSort by performing the merge part of the merge sort
     * (Postcondition: refs1 and refs2 are merged.)
     * @param refs1 the first array to merge
     * @param refs2 the second array to merge
     * @return the merged array
     * (Precondition: refs1 and refs2 are both nonnull)
     */
    private Int[] merge(Int[] refs1, Int[] refs2) {
        Int[] result = new Int[refs1.length + refs2.length];
        System.arraycopy(refs1, 0, result, 0, refs1.length);
        System.arraycopy(refs2, 0, result, refs1.length, refs2.length);

        int[] nums1 = Arrays.stream(refs1).mapToInt(Int::getValue).toArray();
        int[] nums2 = Arrays.stream(refs2).mapToInt(Int::getValue).toArray();

        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            result[k++].setValue(nums1[i] < nums2[j] ? nums1[i++] : nums2[j++]);
        }
        while (i < nums1.length) {
            result[k++].setValue(nums1[i++]);
        }
        while (j < nums2.length) {
            result[k++].setValue(nums2[j++]);
        }

        try {
            Thread.sleep(DRAW_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();

        return result;
    }

    /**
     * Creates rectangles based on nums_.
     * (Postcondition: An array of rectangles based on nums_ is returned.)
     * @return an array of rectangles based on nums_
     * (Precondition: nums_ is nonnull)
     */
    private Rectangle[] createRectangles() {
        int[] values = Arrays.stream(nums_).mapToInt(Int::getValue).toArray();
        Rectangle[] recs = new Rectangle[values.length];
        int barW = (int) (frameDims_.height * 1.0 / values.length);
        double scale = frameDims_.width * 1.0 / VAL_LIMIT;
        for (int i = 0; i < values.length; i++) {
            int barL = (int) (scale * values[i]);
            recs[i] = new Rectangle(0, barW * i, barL, barW);
        }
        return recs;
    }
}
