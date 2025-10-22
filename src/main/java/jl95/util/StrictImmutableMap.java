package jl95.util;

import jl95.lang.I;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface StrictImmutableMap<K, V> {

    V get(K key);
    boolean containsKey(K key);
    Set<K> keySet();
    int size();

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
    default Collection<V> values() {
        return I.of(keySet()).map(this::get).toList();
    }
    default V getOrDefault(K key, V defaultValue) {
        if (!containsKey(key)) return defaultValue;
        return get(key);
    }
}
