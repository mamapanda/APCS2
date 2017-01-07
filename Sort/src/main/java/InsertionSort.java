/**
 * @author Daniel Phan
 */
public class InsertionSort {
    public static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int insert = nums[i];
            int insertPoint = i;
            while (insertPoint > 0 && nums[insertPoint - 1] > insert) {
                int temp = nums[insertPoint];
                nums[insertPoint] = nums[insertPoint - 1];
                nums[--insertPoint] = temp;
            }
        }
    }

    private InsertionSort() {}
}
