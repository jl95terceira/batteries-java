package jl95.lang.primitive;

import jl95.util.DataClass;

import static jl95.lang.SuperPowers.I;
/**
 * A pointer to a value of type boolean. This is useful for simulating pass-by-reference in Java, or for creating mutable references to values.
 */
public class BooleanPointer extends DataClass {

    public interface Applier{
    
        boolean apply(boolean currentValue);
    }
    
    private boolean value;

    public BooleanPointer(boolean value) {this.value = value;}

    @Override protected Iterable<?> data() {
        return I(value);
    }

    public boolean get() {return value;}
    public void set(boolean x) {value = x;}
    public void apply(Applier f) {
        set(f.apply(get()));
    }
}
