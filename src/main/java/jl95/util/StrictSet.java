package jl95.util;

import jl95.lang.Iterable;

public interface StrictSet<T> extends StrictImmutableSet<T> {

    boolean add(T t);
    boolean remove(T o);
    void clear();

    default boolean addAll(Iterable<? extends T> c) {
        var changed = false;
        for (var c_: c) {
            if (!contains(c_)) {
                changed = true;
            }
            add(c_);
        }
        return changed;
    }
    default boolean retainAll(Iterable<? extends T> c) {
        var changed = false;
        for (var x: c) {
            if (!contains(x)) {
                changed = true;
                remove(x);
            }
        }
        return changed;
    }
    default boolean removeAll(Iterable<? extends T> c) {
        var changed = false;
        for (var x: c) {
            if (contains(x)) {
                changed = true;
                remove(x);
            }
        }
        return changed;
    }
}
