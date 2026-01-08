package jl95.util;

import jl95.lang.I;

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
    static VoidAwaitable joined(Iterable<? extends VoidAwaitable> aa) {

        return new VoidAwaitable() {
            @Override
            public void await() {
                I.of(aa).forEach(VoidAwaitable::await);
            }
            @Override
            public Boolean isDone() {
                return I.all(I.of(aa).map(VoidAwaitable::isDone));
            }
        };
    }
}
