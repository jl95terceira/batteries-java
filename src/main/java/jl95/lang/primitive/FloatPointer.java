package jl95.lang.primitive;

import jl95.util.DataClass;

import static jl95.lang.SuperPowers.I;
/**
 * A pointer to a value of type float. This is useful for simulating pass-by-reference in Java, or for creating mutable references to values.
 */
public class FloatPointer extends DataClass {

    public interface Applier{
    
        float apply(float currentValue);
    }
    
    private float value;

    public FloatPointer(float value) {this.value = value;}

    @Override protected Iterable<?> data() {
        return I(value);
    }

    public float get() {return value;}
    public void set(float x) {value = x;}
    public void apply(Applier f) {
        set(f.apply(get()));
    }
}
