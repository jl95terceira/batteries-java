package jl95.lang.primitive;

import jl95.util.DataClass;

import static jl95.lang.SuperPowers.I;
/**
 * A pointer to a value of type double. This is useful for simulating pass-by-reference in Java, or for creating mutable references to values.
 */
public class DoublePointer extends DataClass {

    public interface Applier{
    
        double apply(double currentValue);
    }
    
    private double value;

    public DoublePointer(double value) {this.value = value;}

    @Override protected Iterable<?> data() {
        return I(value);
    }

    public double get() {return value;}
    public void set(double x) {value = x;}
    public void apply(Applier f) {
        set(f.apply(get()));
    }
}
