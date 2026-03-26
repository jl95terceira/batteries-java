package jl95.util;

import jl95.lang.Iterable;

import java.util.Iterator;
import java.util.Objects;

public interface StrictImmutableList<T> extends Iterable<T> {

    T get(int index);
    boolean contains(T o);
    int size();

    default Iterator<T> reversed() {
        if (isEmpty()) {
            return Iterable.<T>empty().iterator();
        }
        return new Iterator<T>() {
            int index = StrictImmutableList.this.size() - 1;
            @Override public boolean hasNext() {
                return index >= 0;
            }
            @Override public T next() {
                return StrictImmutableList.this.get(index);
            }
        };
    }
    default boolean isEmpty() {
        return size() == 0;
    }
    default boolean containsAll(java.lang.Iterable<? extends T> c) {
        for (var c_: c) {
            if (!contains(c_)) return false;
        }
        return true;
    }
    default int indexOf(T o) {
        for (var e: enumerate()) {
            if (Objects.equals(e.a2, o)) {
                return e.a1;
            }
        }
        return -1;
    }
    default int lastIndexOf(T o) {
        for (var e: Iterable.of(this::reversed).enumerate()) {
            if (Objects.equals(e.a2, o)) {
                return size() - e.a1 - 1;
            }
        }
        return -1;
    }
}
