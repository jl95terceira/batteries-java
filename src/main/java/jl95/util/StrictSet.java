package jl95.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import jl95.lang.I;

public interface StrictSet<T> extends I<T> {

    boolean add(T t);
    boolean remove(T o);
    boolean contains(T o);
    int size();
    void clear();

    default boolean isEmpty() {
        return size() == 0;
    }
    default boolean containsAll(Iterable<? extends T> c) {
        for (var c_: c) {
            if (!contains(c_)) return false;
        }
        return true;
    }
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
