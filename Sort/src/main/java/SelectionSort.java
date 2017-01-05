import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Generates random numbers and uses the selection sort to sort them.
 *
 * @author Daniel Phan
 * @version 1.4.16
 */
public class SelectionSort {
    public static void main(String[] args) {
        int count, nums[];
        Scanner in;

        in = new Scanner(System.in);

        System.out.printf("Number of elements in array: ");
        count = in.nextInt();
        nums = genNumbers(count);
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
        int i, upperBound, nums[];
        Random random;

        upperBound = 100;
        nums = new int[count];
        random = new Random();

        for (i = 0; i < count; i++) {
            nums[i] = random.nextInt(upperBound);
        }
        return nums;
    }

    /**
     * Sorts the given array.
     * (Postcondition: nums is changed and sorted)
     * @param nums the array of numbers to sort
     * (Precondition: nums contains numbers)
     */
    public static void selectionSort(int[] nums) {
        int i, j, min, temp;

        for (i = 0; i < nums.length; i++) {
            min = i;
            for (j = i; j < nums.length; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            if (nums[i] != nums[min]) {
                temp = nums[i];
                nums[i] = nums[min];
                nums[min] = temp;
            }
        }
    }
}
