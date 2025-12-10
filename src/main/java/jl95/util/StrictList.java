package jl95.util;

import jl95.lang.I;
import jl95.lang.variadic.Function1;

import java.util.*;

public interface StrictList<T> extends StrictImmutableList<T> {

    T set(int index, T element);
    boolean add(T t);
    void add(int index, T element);
    boolean remove(T o);
    T remove(int index);
    void clear();
    void sort(Comparator<? super T> comparator);

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
