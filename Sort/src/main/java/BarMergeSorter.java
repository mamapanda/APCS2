import java.awt.*;
import javax.swing.*;

public class BarMergeSorter extends JComponent {
    public BarMergeSorter (int[] nums) {
        nums_ = new Int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nums_[i] = new Int(nums[i]);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

    }

    public void start() {
        mergeSort(nums_);
    }

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
        int i = 0, j = 0, k = 0;
        while (i < a1.length && j < a2.length) {
            result[k++] = a1[i].getValue() < a2[j].getValue() ? a1[i++] : a2[j++];
        }
        while (i < a1.length) {
            result[k++] = a1[i++];
        }
        while (j < a2.length) {
            result[k++] = a2[j++];
        }
        return result;
    }

    private Int[] nums_;
}
