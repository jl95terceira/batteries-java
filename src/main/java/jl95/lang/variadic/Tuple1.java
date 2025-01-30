package jl95.lang.variadic;

import static jl95.lang.SuperPowers.*;
import jl95.lang.*;

/**
 * Tuple with 1 elements.
 * @param <A1> type 1
 */
public class Tuple1<A1> extends DataClass {

    /**
     * element 1
     */
    public final A1 a1;
    /**
     * @param a1 element 1
     */
    public Tuple1(A1 a1) {this.a1 = a1;}

    @Override public Iterable<?> data() { return I(a1); }
}