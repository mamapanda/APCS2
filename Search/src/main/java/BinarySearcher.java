public class BinarySearcher extends Searcher {
    public BinarySearcher (int[] nums) {
        super(nums);
    }

    @Override
    public int search(int n) {
        int low, mid, high, visitedCount;

        low = visitedCount = 0;
        mid = -1;
        high = nums_.length - 1;
        while (low < high) {
            mid = (low + high) / 2;
            visitedCount++;
            System.out.println(mid);

            if (nums_[mid] < n) {
                low = mid + 1;
            }
            else if (nums_[mid] > n) {
                high = mid - 1;
            }
            else {
                break;
            }
        }

        return (nums_[mid] == n ? 1 : -1) * visitedCount;
    }
}
