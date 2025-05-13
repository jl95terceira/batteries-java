package jl95.util;

import jl95.util.StrictMap;

public abstract class AutoMapper<K, V> {

    private final StrictMap<K, V> map;

    protected abstract K makeKey();

    public AutoMapper(StrictMap<K, V> map) {
        this.map = map;
    }

    public K put(V value) {
        var key = makeKey();
        map.put(key, value);
        return key;
    }
}
