package jl95.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import jl95.lang.I;

public interface StrictSet<T> {

    boolean add(T t);
    boolean remove(T o);
    boolean contains(T o);
    Iterable<T> iter();
    int size();

    default boolean isEmpty() {
        return size() == 0;
    }
    default boolean containsAll(Collection<? extends T> c) {
        for (var c_: c) {
            if (!contains(c_)) return false;
        }
        return true;
    }
    default boolean addAll(Collection<? extends T> c) {
        var changed = false;
        for (var c_: c) {
            if (!contains(c_)) {
                changed = true;
            }
            add(c_);
        }
        return changed;
    }
    default boolean retainAll(Collection<? extends T> c) {
        var changed = false;
        var selfCopy = I.of(iter()).toSet(); // cannot remove from set while iterating on it - need copy
        for (var x: selfCopy) {
            if (!c.contains(x)) {
                changed = true;
                remove(x);
            }
        }
        return changed;
    }
    default boolean removeAll(Collection<? extends T> c) {
        var changed = false;
        var selfCopy = I.of(iter()).toSet(); // cannot remove from set while iterating on it - need copy
        for (var x: selfCopy) {
            if (c.contains(x)) {
                changed = true;
                remove(x);
            }
        }
        return changed;
    }
    default void clear() {

    }

    static <T> StrictSet<T> of(Set<T> set) {
        return new StrictSet<T>() {
            @Override
            public boolean add(T t) {
                return set.add(t);
            }

            @Override
            public boolean remove(T o) {
                return set.remove(o);
            }

            @Override
            public boolean contains(T o) {
                return set.contains(o);
            }

            @Override
            public Iterable<T> iter() {
                return set;
            }

            @Override
            public int size() {
                return set.size();
            }
        };
    }
}
