package jl95.lang.primitive;

import jl95.util.DataClass;

import static jl95.lang.SuperPowers.I;
/**
 * A pointer to a value of type byte. This is useful for simulating pass-by-reference in Java, or for creating mutable references to values.
 */
public class BytePointer extends DataClass {

    public interface Applier{
    
        byte apply(byte currentValue);
    }
    
    private byte value;

    public BytePointer(byte value) {this.value = value;}

    @Override protected Iterable<?> data() {
        return I(value);
    }

    public byte get() {return value;}
    public void set(byte x) {value = x;}
    public void apply(Applier f) {
        set(f.apply(get()));
    }
}
