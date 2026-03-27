package jl95.lang.variadic;

import static jl95.lang.SuperPowers.*;
import jl95.lang.*;

import jl95.util.DataClass;

/**
 * Tuple with 12 elements.
 * @param <A1> type 1
 * @param <A2> type 2
 * @param <A3> type 3
 * @param <A4> type 4
 * @param <A5> type 5
 * @param <A6> type 6
 * @param <A7> type 7
 * @param <A8> type 8
 * @param <A9> type 9
 * @param <A10> type 10
 * @param <A11> type 11
 * @param <A12> type 12
 */
public class Tuple12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> extends DataClass {

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
     * element 8
     */
    public final A8 a8;
    /**
     * element 9
     */
    public final A9 a9;
    /**
     * element 10
     */
    public final A10 a10;
    /**
     * element 11
     */
    public final A11 a11;
    /**
     * element 12
     */
    public final A12 a12;
    /**
     * @param a1 element 1
     * @param a2 element 2
     * @param a3 element 3
     * @param a4 element 4
     * @param a5 element 5
     * @param a6 element 6
     * @param a7 element 7
     * @param a8 element 8
     * @param a9 element 9
     * @param a10 element 10
     * @param a11 element 11
     * @param a12 element 12
     */
    public Tuple12(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10, A11 a11, A12 a12) {this.a1 = a1; this.a2 = a2; this.a3 = a3; this.a4 = a4; this.a5 = a5; this.a6 = a6; this.a7 = a7; this.a8 = a8; this.a9 = a9; this.a10 = a10; this.a11 = a11; this.a12 = a12;}

    @Override public java.lang.Iterable<?> data() { return I(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12); }
}