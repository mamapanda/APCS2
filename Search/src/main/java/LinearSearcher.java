/**
 * @author Daniel Phan
 */
public class LinearSearcher extends Searcher {
    public LinearSearcher(int[] nums) {
        super(nums);
    }

    @Override
    public int search(int n) {
        int i;

        for (i = 0; nums_[i] < n && i < nums_.length - 1; i++);
        int visitedCount = i + 1;

        return (nums_[i] == n ? 1 : -1) * visitedCount;
    }
}
