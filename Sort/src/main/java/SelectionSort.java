import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.*;

/**
 * Generates random numbers and uses the selection sort to sort them.
 *
 * @author Daniel Phan
 * @version 1.4.16
 */
public class SelectionSort {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.printf("Number of elements in array: ");
        int count = in.nextInt();

        in.close();

        int[] nums = genNumbers(count);

        System.out.printf("Initial Array: %s\n", Arrays.toString(nums));
        selectionSort(nums);
        System.out.printf("Sorted Array: %s\n", Arrays.toString(nums));
    }

    /**
     * Generates an array of random numbers.
     * (Postcondition: an array of length count with random numbers is returned)
     * @param count the number of elements in the array
     * @return an array with random numbers
     * (Precondition: count > 0)
     */
    public static int[] genNumbers(int count) {
        Random random = new Random();

        return IntStream.range(0, count)
                .map(n -> random.nextInt(100))
                .toArray();
    }

    /**
     * Sorts the given array.
     * (Postcondition: nums is changed and sorted)
     * @param nums the array of numbers to sort
     * (Precondition: nums contains numbers)
     */
    public static void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int min = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            if (nums[i] != nums[min]) {
                int temp = nums[i];
                nums[i] = nums[min];
                nums[min] = temp;
            }
        }
    }
}
