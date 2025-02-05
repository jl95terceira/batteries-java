package jl95.lang;

import static jl95.lang.SuperPowers.*;

import java.util.concurrent.Future;

@FunctionalInterface
public interface Awaitable<T> {

    T await();

    static <T> Awaitable<T> of(Future<T> f) { return () -> uncheck(() -> f.get()); }
}
