package jl95.lang.variadic;

import jl95.util.DataClass;
import static jl95.lang.SuperPowers.*;
import jl95.lang.*;

/**
 * Tuple with 7 elements.
 * @param <A1> type 1
 * @param <A2> type 2
 * @param <A3> type 3
 * @param <A4> type 4
 * @param <A5> type 5
 * @param <A6> type 6
 * @param <A7> type 7
 */
public class Tuple7<A1, A2, A3, A4, A5, A6, A7> extends DataClass {

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
     * element 5
     */
    public final A5 a5;
    /**
     * element 6
     */
    public final A6 a6;
    /**
     * element 7
     */
    public final A7 a7;
    /**
     * @param a1 element 1
     * @param a2 element 2
     * @param a3 element 3
     * @param a4 element 4
     * @param a5 element 5
     * @param a6 element 6
     * @param a7 element 7
     */
    public Tuple7(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7) {this.a1 = a1; this.a2 = a2; this.a3 = a3; this.a4 = a4; this.a5 = a5; this.a6 = a6; this.a7 = a7;}

    @Override public Iterable<?> data() { return I(a1, a2, a3, a4, a5, a6, a7); }
}