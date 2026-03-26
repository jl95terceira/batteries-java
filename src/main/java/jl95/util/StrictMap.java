package jl95.util;

import jl95.lang.Iterable;

public interface StrictMap<K, V> extends StrictImmutableMap<K, V> {

    V put(K key, V value);
    V remove(K key);

    default void clear() {

        for (var k: Iterable.of(keySet()).toSet()) {
            remove(k);
        }
    }
    default void putAll(StrictMap<K, V> other) {
        for (var e: other.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }
    default V putIfAbsent(K key, V value) {
        if (!containsKey(key)) {
            put(key, value);
            return null;
        }
        return get(key);
    }
}
