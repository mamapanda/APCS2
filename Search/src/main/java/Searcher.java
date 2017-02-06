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

    public abstract int search(int n);

    public int[] getNums() {
        return nums_;
    }

    protected int[] nums_;
}
