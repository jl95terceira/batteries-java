package jl95terceira.lang.variadic;

import static jl95terceira.lang.SuperPowers.*;
import jl95terceira.lang.*;

/**
 * Tuple with 0 elements.
 */
public class Tuple0 extends DataClass {

    /**

     */
    public Tuple0() {}

    @Override public Iterable<?> data() { return I(); }
}