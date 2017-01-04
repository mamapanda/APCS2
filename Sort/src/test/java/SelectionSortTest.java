import static org.junit.Assert.*;

/**
 * @author Daniel Phan
 */
public class SelectionSortTest {
    @org.junit.Test
    public void selectionSort() throws Exception {
        int[] input, expected;

        input = new int[]{ 100, 32, 0, -3, -444, 666, 3 };
        expected = new int[]{ -444, -3, 0, 3, 32, 100, 666 };
        SelectionSort.selectionSort(input);
        assertArrayEquals(expected, input);

        input = new int[]{ 666, 666, 666, 666, 666, 0 };
        expected = new int[]{ 0, 666, 666, 666, 666, 666 };
        SelectionSort.selectionSort(input);
        assertArrayEquals(expected, input);

        input = new int[]{ 0, 1, 2, 3, 1, 2, 3, 1, 2, 3, 0 };
        expected = new int[]{ 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3 };
        SelectionSort.selectionSort(input);
        assertArrayEquals(expected, input);

        input = new int[]{ 1, 2, 3, 4, 5 };
        expected = new int[]{ 1, 2, 3, 4, 5 };
        SelectionSort.selectionSort(input);
        assertArrayEquals(expected, input);
    }

}
