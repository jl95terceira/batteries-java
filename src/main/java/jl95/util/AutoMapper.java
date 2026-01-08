package jl95.util;

import jl95.lang.variadic.Method2;

public abstract class AutoMapper<K, V> {

    private final Method2<K, V> putMethod;

    protected abstract K makeKey();

    public AutoMapper(Method2<K, V> putMethod) {
        this.putMethod = putMethod;
    }
    public AutoMapper(StrictMap<K, V> map) {
        this(map::put);
    }

    public K put(V value) {
        var key = makeKey();
        putMethod.accept(key, value);
        return key;
    }
}
