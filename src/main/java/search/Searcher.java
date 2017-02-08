package search;

import util.Tuple;

/**
 * The base class for searchers.
 *
 * @author Daniel Phan
 * @version 2.6.17
 */
public abstract class Searcher {
    public Searcher(int[] nums) {
        nums_ = nums;
    }

    public abstract Tuple<Integer, Integer> search(int n);

    public int[] getNums() {
        return nums_;
    }

    protected int[] nums_;
}
