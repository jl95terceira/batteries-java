package jl95.util.impl;

import jl95.lang.I;
import jl95.util.StrictImmutableList;
import jl95.util.StrictList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

public class StrictListWithInternalNativeList<T> implements StrictList<T> {

    private final java.util.List<T> list;

    public StrictListWithInternalNativeList(java.util.List<T> list) {
        this.list = list;
    }

    public java.util.List<T> getInternalList() {
        return list;
    }

    @Override public boolean add(T t) {
        return list.add(t);
    }
    @Override public void add(int index, T element) {
        list.add(index, element);
    }
    @Override public T get(int index) {
        return list.get(index);
    }
    @Override public T set(int index, T element) {
        return list.set(index, element);
    }
    @Override public boolean remove(T o) {
        return list.remove(o);
    }
    @Override public T remove(int index) {
        return list.remove(index);
    }
    @Override public boolean contains(T o) {
        return list.contains(o);
    }
    @Override public Iterator<T> iterator() {
        return list.iterator();
    }
    @Override public int size() {
        return list.size();
    }
    @Override public void clear() {
        list.clear();
    }
    @Override public void sort(Comparator<? super T> comparator) {
        list.sort(comparator);
    }
    @Override public String toString() {
        return "strict(%s)".formatted(list);
    }
    @Override public int hashCode() {
        return list.hashCode();
    }
    @Override public boolean equals(Object other) {
        return other instanceof StrictImmutableList<?> otherList
                ? (size() == otherList.size() && I.zip(this,otherList).all(t -> Objects.equals(t.a1,t.a2)))
                : list.equals(other);
    }
}
