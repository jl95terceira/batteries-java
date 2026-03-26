package jl95.lang;

import jl95.lang.variadic.Function1;
import jl95.util.DataClass;

import static jl95.lang.SuperPowers.I;

public class Pointer<T> extends DataClass {

    private T value;

    public Pointer(T value) {this.value = value;}

    @Override protected Iterable<?> data() {
        return I(value);
    }

    public T    get() {return value;}
    public void set(T x) {value = x;}
    public void apply(Function1<T, T> f) {
        value = f.apply(value);
    }
}
