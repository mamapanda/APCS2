import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.*;

public class SearcherMain {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.printf("Number of values to put in the array: ");
        int[] nums = genNumbers(in.nextInt());
        in.nextLine();

        System.out.printf("Unsorted: %s\n", Arrays.toString(nums));
        selectionSort(nums);
        System.out.printf("Sorted: %s\n", Arrays.toString(nums));

        System.out.printf("Number to search for: ");
        int searchValue = in.nextInt();
        in.nextLine();

        System.out.printf("Search type: (1) Linear, (2) Binary: ");
        Searcher searcher;
        if (in.nextInt() == 1) {
            searcher = new LinearSearcher(nums);
        }
        else {
            searcher = new BinarySearcher(nums);
        }

        int searchCount = searcher.search(searchValue);

        System.out.printf("The value %d was %s in the above array.\n",
                          searchValue,
                          searchCount < 0 ? "NOT FOUND" : "FOUND");
        System.out.printf("Number of cells visited: %d\n", Math.abs(searchCount));
    }

    private final static int MIN_VALUE = 1;
    private final static int UPPER_BOUND = 100;

    private static int[] genNumbers(int count) {
        Random rand = new Random();
        return IntStream.range(0, count)
                .map(n -> rand.nextInt(UPPER_BOUND - MIN_VALUE) + MIN_VALUE)
                .toArray();
    }

    private static void selectionSort(int[] nums) {
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
