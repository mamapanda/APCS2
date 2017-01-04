import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Daniel Phan
 */
@RunWith(Parameterized.class)
public class SelectionSortTest {
    public SelectionSortTest(int[] input, int[] expected) {
        input_ = input;
        expected_ = expected;
    }

    @Parameterized.Parameters
    public static Collection<int[][]> cases() {
        return Arrays.asList(new int[][][]{{
                new int[]{100, 32, 0, -3, -444, 666, 3},
                new int[]{-444, -3, 0, 3, 32, 100, 666}
        }, {
                new int[]{666, 666, 666, 666, 666, 0},
                new int[]{0, 666, 666, 666, 666, 666}
        }, {
                new int[]{0, 1, 2, 3, 1, 2, 3, 1, 2, 3, 0},
                new int[]{0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3}
        }, {
                new int[]{1, 2, 3, 4, 5},
                new int[]{1, 2, 3, 4, 5}
        }
        });
    }

    @org.junit.Test
    public void selectionSort() throws Exception {
        SelectionSort.selectionSort(input_);
        assertEquals(Arrays.toString(expected_), Arrays.toString(input_));
    }

    private int[] input_;
    private int[] expected_;
}
