package jl95.util;

import static jl95.lang.SuperPowers.uncheck;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import jl95.lang.I;

public interface UFuture<T> {

    boolean cancel(boolean mayInterruptIfRunning);
    boolean isCancelled();
    boolean isDone();
    T get();
    T get(long timeout, TimeUnit unit);

    default UVoidFuture voided() {
        return UVoidFuture.of(this);
    }

    static <T> UFuture<T> of(Future<T> f) {
        return new UFuture<T>() {
            @Override public boolean cancel(boolean mayInterruptIfRunning) {
                return f.cancel(mayInterruptIfRunning);
            }
            @Override public boolean isCancelled() {
                return f.isCancelled();
            }
            @Override public boolean isDone() {
                return f.isDone();
            }
            @Override public T get() {
                return uncheck(() -> f.get());
            }
            @Override public T get(long timeout, TimeUnit unit) {
                return uncheck(() -> f.get(timeout, unit));
            }
        };
    }
    static <T> UFuture<List<T>> joined(Iterable<? extends UFuture<T>> aa) {

        return new UFuture<>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return I.of(aa).all(f -> f.cancel(mayInterruptIfRunning));
            }
            @Override
            public boolean isCancelled() {
                return I.of(aa).all(UFuture::isCancelled);
            }
            @Override
            public boolean isDone() {
                return I.all(I.of(aa).map(UFuture::isDone));
            }
            @Override
            public List<T> get() {
                return I.of(aa).map(UFuture::get).toList();
            }
            @Override
            public List<T> get(long timeout, TimeUnit unit) {
                return I.of(aa).map(f -> f.get(timeout, unit)).toList();
            }
        };
    }
}
