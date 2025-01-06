package jl95terceira.lang.variadic;

import static jl95terceira.lang.SuperPowers.*;
import jl95terceira.lang.*;

/**
 * Tuple with 4 elements.
 * @param <A1> type 1
 * @param <A2> type 2
 * @param <A3> type 3
 * @param <A4> type 4
 */
public class Tuple4<A1, A2, A3, A4> extends DataClass {

    /**
     * element 1
     */
    public final A1 a1;
    /**
     * element 2
     */
    public final A2 a2;
    /**
     * element 3
     */
    public final A3 a3;
    /**
     * element 4
     */
    public final A4 a4;
    /**
     * @param a1 element 1
     * @param a2 element 2
     * @param a3 element 3
     * @param a4 element 4
     */
    public Tuple4(A1 a1, A2 a2, A3 a3, A4 a4) {this.a1 = a1; this.a2 = a2; this.a3 = a3; this.a4 = a4;}

    @Override public Iterable<?> data() { return I(a1, a2, a3, a4); }
}