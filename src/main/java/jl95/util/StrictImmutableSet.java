package jl95.util;

import jl95.lang.I;

public interface StrictImmutableSet<T> extends I<T> {

    boolean contains(T o);
    int size();

    default boolean isEmpty() {
        return size() == 0;
    }
    default boolean containsAll(Iterable<? extends T> c) {
        for (var c_: c) {
            if (!contains(c_)) return false;
        }
        return true;
    }
}
