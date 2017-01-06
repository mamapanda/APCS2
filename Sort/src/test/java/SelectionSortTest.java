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
    public SelectionSortTest(int[] input) {
        input_ = input;
    }

    @Parameterized.Parameters
    public static Collection<int[]> cases() {
        return Arrays.asList(new int[][]{
                {100, 32, 0, -3, -444, 666, 3},
                {42, 9, 17, 54, 602, -3, 54, 999, -11},
                {666, 666, 666, 666, 666, 0},
                {},
                {1, 2, 3, 4, 5}
        });
    }

    @org.junit.Test
    public void selectionSort() throws Exception {
        int expected[];

        expected = Arrays.stream(input_).sorted().toArray();

        InsertionSort.insertionSort(input_);
        assertEquals(Arrays.toString(expected), Arrays.toString(input_));
    }

    private int[] input_;
}
