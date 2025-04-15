package jl95.lang;

import static jl95.lang.SuperPowers.uncheck;

import java.util.concurrent.Future;

public interface Awaitable extends Completable {

    void await();

    static <T> Awaitable of(Future<? extends T> f) { return new Awaitable() {
        @Override
        public void await() {
            uncheck(() -> f.get());
        }
        @Override
        public Boolean isDone() {
            return f.isDone();
        }
    }; }
    static Awaitable joined(Iterable<? extends Awaitable> aa) {

        return new Awaitable() {
            @Override
            public void await() {
                I.of(aa).forEach(Awaitable::await);
            }
            @Override
            public Boolean isDone() {
                return I.all(I.of(aa).map(Awaitable::isDone));
            }
        };
    }
}
