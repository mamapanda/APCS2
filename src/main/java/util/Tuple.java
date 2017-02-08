package util;

/**
 * @author Daniel Phan
 */
public class Tuple<T, U> {
    public final T fst;
    public final U snd;

    public Tuple(T fst, U snd) {
        this.fst = fst;
        this.snd = snd;
    }
}
