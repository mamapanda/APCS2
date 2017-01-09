/**
 * @author Daniel Phan
 */
public class MergeSort {
    public static void main(String[] args) {

    }

    public static int[] mergeSort(int[] nums) {
        if (nums.length == 1 || nums.length == 0) {
            return nums;
        }

        int[] a1 = new int[nums.length / 2];
        int[] a2 = new int[(nums.length + 1) / 2];

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

    private static int[] merge(int[] a1, int[] a2) {
        int[] result = new int[a1.length + a2.length];
        int i = 0, j = 0, k = 0;
        while (i < a1.length && j < a2.length) {
            result[k++] = a1[i] < a2[j] ? a1[i++] : a2[j++];
        }
        while (i < a1.length) {
            result[k++] = a1[i++];
        }
        while (j < a2.length) {
            result[k++] = a2[j++];
        }
        return result;
    }
}
