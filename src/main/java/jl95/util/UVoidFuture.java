package jl95.util;

import static jl95.lang.SuperPowers.uncheck;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import jl95.lang.I;

public interface UVoidFuture {

    boolean cancel(boolean mayInterruptIfRunning);
    boolean isCancelled();
    boolean isDone();
    void get();
    void get(long timeout, TimeUnit unit);

    static <T> UVoidFuture of(UFuture<T> f) {
        return new UVoidFuture() {
            @Override public boolean cancel(boolean mayInterruptIfRunning) {
                return f.cancel(mayInterruptIfRunning);
            }
            @Override public boolean isCancelled() {
                return f.isCancelled();
            }
            @Override public boolean isDone() {
                return f.isDone();
            }
            @Override public void get() {
                f.get();
            }
            @Override public void get(long timeout, TimeUnit unit) {
                f.get(timeout, unit);
            }
        };
    }
    static <T> UVoidFuture of(Future<T> f) {
        return of(UFuture.of(f));
    }
    static <T> UVoidFuture joined(Iterable<? extends UVoidFuture> aa) {

        return new UVoidFuture() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return I.of(aa).all(f -> f.cancel(mayInterruptIfRunning));
            }
            @Override
            public boolean isCancelled() {
                return I.of(aa).all(UVoidFuture::isCancelled);
            }
            @Override
            public boolean isDone() {
                return I.all(I.of(aa).map(UVoidFuture::isDone));
            }
            @Override
            public void get() {
                I.of(aa).forEach(UVoidFuture::get);
            }
            @Override
            public void get(long timeout, TimeUnit unit) {
                I.of(aa).forEach(f -> f.get(timeout, unit));
            }
        };
    }
}
