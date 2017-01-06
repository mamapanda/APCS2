/**
 * @author Daniel Phan
 */
public class InsertionSort {
    public static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int insert = nums[i];
            int insertPoint = i;
            while (insertPoint > 0 && nums[insertPoint - 1] > insert) {
                nums[insertPoint] = nums[insertPoint - 1];
                insertPoint--;
            }
            nums[insertPoint] = insert;
        }
    }

    private InsertionSort() {}
}
