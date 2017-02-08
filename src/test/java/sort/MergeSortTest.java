package sort;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Daniel Phan
 */
@RunWith(Parameterized.class)
public class MergeSortTest {
    public MergeSortTest(int[] input, int[] expected) {
        input_ = input;
        expected_ = expected;
    }

    @Parameterized.Parameters
    public static Collection<int[][]> cases() {
        int[][] inputs = new int[][]{
                {100, 32, 0, -3, -444, 666, 3},
                {42, 9, 17, 54, 602, -3, 54, 999, -11},
                {666, 666, 666, 666, 666, 0},
                {},
                {1, 2, 3, 4, 5}
        };

        int[][][] cases = new int[inputs.length][2][inputs.length];
        for (int i = 0; i < cases.length; i++) {
            cases[i][0] = inputs[i];
            cases[i][1] = Arrays.stream(inputs[i]).sorted().toArray();
        }

        return Arrays.asList(cases);
    }

    @org.junit.Test
    public void mergeSort() throws Exception {
        int[] result = MergeSort.mergeSort(input_);
        assertEquals(Arrays.toString(expected_), Arrays.toString(result));
    }

    private int[] input_;
    private int[] expected_;
}
