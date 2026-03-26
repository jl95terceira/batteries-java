package jl95.util;

import jl95.lang.Iterable;

import static jl95.lang.SuperPowers.*;

import java.util.List;
import java.util.concurrent.Future;

@Deprecated
public interface Awaitable<T> extends Completable {

    T await();

    default VoidAwaitable ignored() {
        return new VoidAwaitable() {
            @Override
            public void await() {
                Awaitable.this.await();
            }
            @Override
            public Boolean isDone() {
                return Awaitable.this.isDone();
            }
        };
    }

    static <T> Awaitable<T> of(Future<? extends T> f) { return new Awaitable<>() {
        @Override
        public T await() {
            return uncheck(() -> f.get());
        }
        @Override
        public Boolean isDone() {
            return f.isDone();
        }
    }; }
    static <T> Awaitable<List<T>> joined(java.lang.Iterable<? extends Awaitable<T>> aa) {

        return new Awaitable<>() {
            @Override
            public List<T> await() {
                return Iterable.of(aa).map(Awaitable::await).toList();
            }
            @Override
            public Boolean isDone() {
                return Iterable.all(Iterable.of(aa).map(Awaitable::isDone));
            }
        };
    }
}
