package jl95.util.impl;

import jl95.util.StrictImmutableSet;
import jl95.util.StrictSet;

import java.util.Iterator;

import static jl95.lang.SuperPowers.I;

public class StrictSetWithInternalNativeSet<T> implements StrictSet<T> {

    private final java.util.Set<T> set;

    public StrictSetWithInternalNativeSet(java.util.Set<T> set) {
        this.set = set;
    }

    public java.util.Set<T> getInternalSet() {
        return set;
    }

    @Override public boolean add(T t) {
        return set.add(t);
    }
    @Override public boolean remove(T o) {
        return set.remove(o);
    }
    @Override public boolean contains(T o) {
        return set.contains(o);
    }
    @Override public Iterator<T> iterator() {
        return set.iterator();
    }
    @Override public int size() {
        return set.size();
    }
    @Override public void clear() {
        set.clear();
    }
    @Override public String toString() {
        return "strict(%s)".formatted(set);
    }
    @Override public int hashCode() {
        return set.hashCode();
    }
    @Override public boolean equals(Object other) {
        return other instanceof StrictImmutableSet<?> otherList
                ? (size() == otherList.size() && I(this,otherList).flatmap(xx -> xx).toSet().size() == size())
                : set.equals(other);
    }
}
