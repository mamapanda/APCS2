package util;

import java.util.function.Function;

/**
 * @author Daniel Phan
 */
public abstract class Either<T, U> {
    public abstract <V> V either(Function<T, V> lFunc, Function<U, V> rFunc);

    @Override
    public abstract String toString();

    public boolean isLeft() {
        return this instanceof Left;
    }

    public boolean isRight() {
        return this instanceof Right;
    }

    public static class Left<V, W> extends Either<V, W> {
        public final V value;

        public Left(V value) {
            this.value = value;
        }

        @Override
        public <X> X either(Function<V, X> lFunc, Function<W, X> rFunc) {
            return lFunc.apply(value);
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    public static class Right<V, W> extends Either<V, W> {
        public final W value;

        public Right(W value) {
            this.value = value;
        }

        @Override
        public <X> X either(Function<V, X> lFunc, Function<W, X> rFunc) {
            return rFunc.apply(value);
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }
}
