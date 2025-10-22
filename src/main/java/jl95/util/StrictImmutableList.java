package jl95.util;

import jl95.lang.I;

import java.util.Comparator;

public interface StrictImmutableList<T> extends I<T> {

    boolean add(T t);
    T get(int index);
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
