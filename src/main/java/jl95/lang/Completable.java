package jl95.lang;

import static jl95.lang.SuperPowers.uncheck;

import java.util.concurrent.Future;

public interface Completable {

    Boolean isDone();

    static <T> Completable of(Future<? extends T> f) { return f::isDone; }
    static Completable joined(Iterable<? extends Completable> aa) {

        return () -> I.all(I.of(aa).map(Completable::isDone));
    }
}
