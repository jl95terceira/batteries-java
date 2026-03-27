package jl95.lang.variadic;

import static jl95.lang.SuperPowers.*;
import jl95.lang.*;

import jl95.util.DataClass;

/**
 * Tuple with 2 elements.
 * @param <A1> type 1
 * @param <A2> type 2
 */
public class Tuple2<A1, A2> extends DataClass {

    /**
     * element 1
     */
    public final A1 a1;
    /**
     * element 2
     */
    public final A2 a2;
    /**
     * @param a1 element 1
     * @param a2 element 2
     */
    public Tuple2(A1 a1, A2 a2) {this.a1 = a1; this.a2 = a2;}

    @Override public java.lang.Iterable<?> data() { return I(a1, a2); }
}