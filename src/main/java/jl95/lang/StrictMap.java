package jl95.lang;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

import jl95.lang.variadic.Tuple2;

public interface StrictMap<K, V> {

    V get(K key);
    V put(K key, V value);
    V remove(K key);
    boolean containsKey(K key);
    Set<K> keySet();
    int size();

    default void clear() {

        for (var k: I.of(keySet()).toSet()) {
            remove(k);
        }
    }
    default boolean containsValue(V value) {
        return I.any(I.of(keySet()).map(k -> {
            var v = get(k);
            if (v == null) return value == null;
            return get(k).equals(value);
        }));
    }
    default Iterable<Map.Entry<K, V>> entrySet() {
        return I.of(keySet()).map(k -> new AbstractMap.SimpleEntry<>(k, get(k)));
    }
    default boolean isEmpty() {
        for (var x: keySet()) {
            return false;
        }
        return true;
    }
    default void putAll(StrictMap<K, V> other) {
        for (var e: other.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }
    default Collection<V> values() {
        return I.of(keySet()).map(this::get).toList();
    }
    default V getOrDefault(K key, V defaultValue) {
        if (!containsKey(key)) return defaultValue;
        return get(key);
    }
    default V putIfAbsent(K key, V value) {
        if (!containsKey(key)) {
            put(key, value);
            return null;
        }
        return get(key);
    }
}
