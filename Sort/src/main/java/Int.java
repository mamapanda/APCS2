/**
 * A wrapper around the int primitive.
 *
 * @author Daniel Phan
 * @version 1.10.16
 */
public class Int {
    /**
     * Constructs a new Int with the given int
     * (Postcondition: n is initialized)
     * @param n the int this wraps around
     * (Precondition: n is an int)
     */
    public Int(int n) {
        n_ = n;
    }

    /**
     * Returns the int this wraps around.
     * (Postcondition: n_ is returned)
     * @return the int this wraps around
     * (Precondition: n_ is initialized)
     */
    public int getValue() {
        return n_;
    }

    /**
     * Sets the int this wraps around to n
     * (Postcondition n_ is set to n)
     * @param n the new value for n_
     * (Precondition: n is an integer)
     */
    public void setValue(int n) {
        n_ = n;
    }

    /**
     * Returns the string representation of this
     * (Postcondition: a string representing n_ is returned)
     * @return a string representing n_
     * (Precondition: n_ is initialized)
     */
    @Override
    public String toString() {
        return String.valueOf(n_);
    }

    /**
     * Returns a new instance of Int that wraps around n
     * (Postcondition:  a new Int wrapped around n is returned)
     * @param n the int to wrap around
     * @return a new Int wrapped around n
     * (Precondition: n is an integer)
     */
    public static Int valueOf(int n) {
        return new Int(n);
    }

    private int n_; //the int Int wraps around
}
