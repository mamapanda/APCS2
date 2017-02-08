package search;

import util.Tuple;

/**
 * Class that does binary searching.
 *
 * @author Daniel Phan
 * @version 2.6.17
 */
public class BinarySearcher extends Searcher {
    public BinarySearcher(int[] nums) {
        super(nums);
    }

    @Override
    public Tuple<Integer, Integer> search(int n) {
        int i = -1;
        int visitedCount = 0;
        int low = 0;
        int high = nums_.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            visitedCount++;

            if (nums_[mid] < n) {
                low = mid + 1;
            } else if (nums_[mid] > n) {
                high = mid - 1;
            } else {
                i = mid;
                break;
            }
        }

        return new Tuple<>(i, visitedCount);
    }
}
