/**
 * A wrapper around the int primitive.
 *
 * @author Daniel Phan
 * @version 1.10.16
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

    public static Int valueOf(int n) {
        return new Int(n);
    }

    private int n_;
}
