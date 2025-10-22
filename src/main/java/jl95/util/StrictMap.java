package jl95.util;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import jl95.lang.I;

import jl95.lang.variadic.Tuple2;

public interface StrictMap<K, V> extends StrictImmutableMap<K, V> {

    V put(K key, V value);
    V remove(K key);

    default void clear() {

        for (var k: I.of(keySet()).toSet()) {
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
