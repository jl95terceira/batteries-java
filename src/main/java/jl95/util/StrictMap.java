package jl95.util;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import jl95.lang.I;

import jl95.lang.variadic.Tuple2;

public interface StrictMap<K, V> {

    V get(K key);
    V put(K key, V value);
    V remove(K key);
    boolean containsKey(K key);
    Set<K> keySet();
    int size();

    default void clear() {
        for (var k: keySet()) {
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

    static <K, V> StrictMap<K, V> of(Map<K, V> map) {
        return new StrictMap<>() {

            @Override
            public V get(K key) {
                return map.get(key);
            }

            @Override
            public V put(K key, V value) {
                return map.put(key, value);
            }

            @Override
            public V remove(K key) {
                return map.remove(key);
            }

            @Override
            public boolean containsKey(K key) {
                return map.containsKey(key);
            }

            @Override
            public Set<K> keySet() {
                return map.keySet();
            }

            @Override
            public int size() {
                return map.size();
            }
        };
    }
}
