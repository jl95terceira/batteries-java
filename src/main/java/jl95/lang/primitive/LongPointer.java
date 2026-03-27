package jl95.lang.primitive;

import jl95.util.DataClass;

import static jl95.lang.SuperPowers.I;
/**
 * A pointer to a value of type long. This is useful for simulating pass-by-reference in Java, or for creating mutable references to values.
 */
public class LongPointer extends DataClass {

    public interface Applier{
    
        long apply(long currentValue);
    }
    
    private long value;

    public LongPointer(long value) {this.value = value;}

    @Override protected Iterable<?> data() {
        return I(value);
    }

    public long get() {return value;}
    public void set(long x) {value = x;}
    public void apply(Applier f) {
        set(f.apply(get()));
    }
}
