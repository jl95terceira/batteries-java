package jl95.lang.variadic;

import static jl95.lang.SuperPowers.*;
import jl95.lang.*;

import jl95.util.DataClass;

/**
 * Tuple with 0 elements.
 */
public class Tuple0 extends DataClass {

    /**

     */
    public Tuple0() {}

    @Override public java.lang.Iterable<?> data() { return I(); }
}