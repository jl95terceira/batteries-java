package jl95.lang;

import static jl95.lang.SuperPowers.*;

import java.util.List;
import java.util.concurrent.Future;

public interface TypedAwaitable<T> extends Completable {

    T await();

    default Awaitable ignored() {
        return new Awaitable() {
            @Override
            public void await() {
                TypedAwaitable.this.await();
            }
            @Override
            public Boolean isDone() {
                return TypedAwaitable.this.isDone();
            }
        };
    }

    static <T> TypedAwaitable<T> of(Future<? extends T> f) { return new TypedAwaitable<>() {
        @Override
        public T await() {
            return uncheck(() -> f.get());
        }
        @Override
        public Boolean isDone() {
            return f.isDone();
        }
    }; }
    static <T> TypedAwaitable<List<T>> joined(Iterable<? extends TypedAwaitable<T>> aa) {

        return new TypedAwaitable<>() {
            @Override
            public List<T> await() {
                return I.of(aa).map(TypedAwaitable::await).toList();
            }
            @Override
            public Boolean isDone() {
                return I.all(I.of(aa).map(TypedAwaitable::isDone));
            }
        };
    }
}
