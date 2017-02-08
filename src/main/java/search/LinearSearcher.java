package search;

import util.Tuple;

/**
 * @author Daniel Phan
 */
public class LinearSearcher extends Searcher {
    public LinearSearcher(int[] nums) {
        super(nums);
    }

    @Override
    public Tuple<Integer, Integer> search(int n) {
        int i;

        for (i = 0; nums_[i] < n && i < nums_.length - 1; i++);

        return new Tuple<>((nums_[i] == n ? i : -1), i + 1);
    }
}
