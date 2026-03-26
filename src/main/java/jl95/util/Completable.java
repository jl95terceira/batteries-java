package jl95.util;

import jl95.lang.Iterable;

import static jl95.lang.SuperPowers.uncheck;

import java.util.concurrent.Future;

public interface Completable {

    Boolean isDone();

    static <T> Completable of(Future<? extends T> f) { return f::isDone; }
    static Completable joined(java.lang.Iterable<? extends Completable> aa) {

        return () -> Iterable.all(Iterable.of(aa).map(Completable::isDone));
    }
}
