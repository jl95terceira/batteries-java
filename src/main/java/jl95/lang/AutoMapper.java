package jl95.lang;

import static jl95.lang.SuperPowers.strict;

import java.util.Map;

public abstract class AutoMapper<K, V> {

    private final StrictMap<K, V> map;

    protected abstract K makeKey();

    public AutoMapper(StrictMap<K, V> map) {
        this.map = map;
    }
    public AutoMapper(Map      <K, V> map) {
        this(strict(map));
    }

    public K put   (V value) {

        var key = makeKey();
        map.put(key, value);
        return key;
    }
    public V remove(K key) {

        return map.remove(key);
    }
}
