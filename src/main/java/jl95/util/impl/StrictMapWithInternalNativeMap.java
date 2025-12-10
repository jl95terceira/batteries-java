package jl95.util.impl;

import jl95.util.StrictImmutableMap;
import jl95.util.StrictMap;

import java.util.Set;

import static jl95.lang.SuperPowers.I;

public class StrictMapWithInternalNativeMap<K,V> implements StrictMap<K,V> {

    private final java.util.Map<K, V> map;

    public StrictMapWithInternalNativeMap(java.util.Map<K, V> map) {
        this.map = map;
    }

    public java.util.Map<K, V> getInternalMap() {
        return map;
    }

    @Override public V get(K key) {
        return map.get(key);
    }
    @Override public V put(K key, V value) {
        return map.put(key, value);
    }
    @Override public V remove(K key) {
        return map.remove(key);
    }
    @Override public boolean containsKey(K key) {
        return map.containsKey(key);
    }
    @Override public Set<K> keySet() {
        return map.keySet();
    }
    @Override public int size() {
        return map.size();
    }
    @Override public String toString() {
        return "strict(%s)".formatted(map);
    }
    @Override public int hashCode() {
        return map.hashCode();
    }
    @Override public boolean equals(Object other) {
        return other instanceof StrictImmutableMap<?,?> otherList
                ? (size() == otherList.size()
                && I(this.entrySet(),otherList.entrySet()).flatmap(xx -> xx).toSet().size() == size())
                : map.equals(other);
    }
}
