package jl95.lang;

import jl95.util.DataClass;

import static jl95.lang.SuperPowers.I;
/**
 * A pointer to a value of generic type. This is useful for simulating pass-by-reference in Java, or for creating mutable references to values.
 * @param <T> value type
 */
public class Pointer<T> extends DataClass {

    public interface Applier<T>{
    
        T apply(T currentValue);
    }
    
    private T value;

    public Pointer(T value) {this.value = value;}

    @Override protected Iterable<?> data() {
        return I(value);
    }

    public T get() {return value;}
    public void set(T x) {value = x;}
    public void apply(Applier<T> f) {
        set(f.apply(get()));
    }
}
