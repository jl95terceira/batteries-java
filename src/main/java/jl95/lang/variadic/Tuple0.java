package jl95.lang.variadic;

import static jl95.lang.SuperPowers.*;
import jl95.lang.*;

/**
 * Tuple with 0 elements.
 */
public class Tuple0 extends DataClass {

    /**

     */
    public Tuple0() {}

    @Override public Iterable<?> data() { return I(); }
}