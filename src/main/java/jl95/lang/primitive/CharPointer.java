package jl95.lang.primitive;

import jl95.util.DataClass;

import static jl95.lang.SuperPowers.I;
/**
 * A pointer to a value of type char. This is useful for simulating pass-by-reference in Java, or for creating mutable references to values.
 */
public class CharPointer extends DataClass {

    public interface Applier{
    
        char apply(char currentValue);
    }
    
    private char value;

    public CharPointer(char value) {this.value = value;}

    @Override protected Iterable<?> data() {
        return I(value);
    }

    public char get() {return value;}
    public void set(char x) {value = x;}
    public void apply(Applier f) {
        set(f.apply(get()));
    }
}
