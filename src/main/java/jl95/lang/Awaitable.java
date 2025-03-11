package jl95.lang;

import static jl95.lang.SuperPowers.*;

import java.util.concurrent.Future;

public interface Awaitable<T> {

    T       await();
    Boolean isDone();

    static <T> Awaitable<T> of(Future<T> f) { return new Awaitable<T>() {
        @Override
        public T await() {
            return uncheck(() -> f.get());
        }

        @Override
        public Boolean isDone() {
            return f.isDone();
        }
    }; }
}
