/**
 * @author Daniel Phan
 */
public class Int {
    public Int(int n) {
        n_ = n;
    }

    public int getValue() {
        return n_;
    }

    public void setValue(int n) {
        n_ = n;
    }

    @Override
    public String toString() {
        return String.valueOf(n_);
    }

    private int n_;
}
