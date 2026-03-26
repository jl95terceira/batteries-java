package jl95.util;

import jl95.lang.Iterable;

import static jl95.lang.SuperPowers.uncheck;

import java.util.concurrent.Future;

@Deprecated
public interface VoidAwaitable extends Completable {

    void await();

    static <T> VoidAwaitable of(Future<? extends T> f) { return new VoidAwaitable() {
        @Override
        public void await() {
            uncheck(() -> f.get());
        }
        @Override
        public Boolean isDone() {
            return f.isDone();
        }
    }; }
    static VoidAwaitable joined(java.lang.Iterable<? extends VoidAwaitable> aa) {

        return new VoidAwaitable() {
            @Override
            public void await() {
                Iterable.of(aa).forEach(VoidAwaitable::await);
            }
            @Override
            public Boolean isDone() {
                return Iterable.all(Iterable.of(aa).map(VoidAwaitable::isDone));
            }
        };
    }
}
