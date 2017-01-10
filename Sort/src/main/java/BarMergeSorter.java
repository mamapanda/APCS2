import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;

public class BarMergeSorter extends JComponent {
    public BarMergeSorter (int barCount, Dimension frameDims) {
        Random random = new Random();
        frameDims_ = frameDims;
        nums_ = new Int[barCount];
        for (int i = 0; i < nums_.length; i++) {
            nums_[i] = new Int(random.nextInt(UPPER_BOUND - LOWER_BOUND) + LOWER_BOUND);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        for (Rectangle r: createRectangles()) {
            g2.setColor(new Color(148, 0, 211));
            g2.fill(r);
            g2.setColor(Color.WHITE);
            g2.draw(r);
        }
    }

    public void run() {
        mergeSort(nums_);
    }

    @Override
    public String toString() {
        return Arrays.deepToString(nums_);
    }

    private Int[] nums_; //the array of numbers to sort
    private Dimension frameDims_; //the dimensions of the JFrame
    private static final int LOWER_BOUND = 5; //the inclusive lower bound of the random values
    private static final int UPPER_BOUND = 100; //the exclusive upper bound of the random values
    private static final int DRAW_DELAY = 500; //the drawing delay in ms

    private Int[] mergeSort(Int[] nums) {
        if (nums.length == 1 || nums.length == 0) {
            return nums;
        }

        Int[] a1 = new Int[nums.length / 2];
        Int[] a2 = new Int[(nums.length + 1) / 2];

        for (int i = 0; i < nums.length; i++) {
            if (i < nums.length / 2) {
                a1[i] = nums[i];
            }
            else {
                a2[i - nums.length / 2] = nums[i];
            }
        }
        return merge(mergeSort(a1), mergeSort(a2));
    }

    private Int[] merge(Int[] a1, Int[] a2) {
        Int[] result = new Int[a1.length + a2.length];
        System.arraycopy(a1, 0, result, 0, a1.length);
        System.arraycopy(a2, 0, result, a1.length, a2.length);

        int[] a1int = Arrays.stream(a1).mapToInt(Int::getValue).toArray();
        int[] a2int = Arrays.stream(a2).mapToInt(Int::getValue).toArray();

        int i = 0, j = 0, k = 0;
        while (i < a1int.length && j < a2int.length) {
            result[k++].setValue(a1int[i] < a2int[j] ? a1int[i++] : a2int[j++]);
        }
        while (i < a1int.length) {
            result[k++].setValue(a1int[i++]);
        }
        while (j < a2int.length) {
            result[k++].setValue(a2int[j++]);
        }

        try {
            Thread.sleep(DRAW_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();

        return result;
    }

    private Rectangle[] createRectangles() {
        int[] values = Arrays.stream(nums_).mapToInt(Int::getValue).toArray();
        Rectangle[] recs = new Rectangle[values.length];
        int barW = (int) (frameDims_.height * 1.0 / values.length);
        double scale = frameDims_.width * 1.0 / UPPER_BOUND;
        for (int i = 0; i < values.length; i++) {
            int barL = (int) (scale * values[i]);
            recs[i] = new Rectangle(0, barW * i, barL, barW);
        }
        return recs;
    }
}
