package jl95.lang.variadic;

/**
 * An interface for a method (a function that returns {@code void}) that takes 19 arguments. <br/>
 * @param <A1> type of argument nr 1
 * @param <A2> type of argument nr 2
 * @param <A3> type of argument nr 3
 * @param <A4> type of argument nr 4
 * @param <A5> type of argument nr 5
 * @param <A6> type of argument nr 6
 * @param <A7> type of argument nr 7
 * @param <A8> type of argument nr 8
 * @param <A9> type of argument nr 9
 * @param <A10> type of argument nr 10
 * @param <A11> type of argument nr 11
 * @param <A12> type of argument nr 12
 * @param <A13> type of argument nr 13
 * @param <A14> type of argument nr 14
 * @param <A15> type of argument nr 15
 * @param <A16> type of argument nr 16
 * @param <A17> type of argument nr 17
 * @param <A18> type of argument nr 18
 * @param <A19> type of argument nr 19
 */
public interface Method19<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19> {

    /**
     * interface method
     * @param a1 argument nr 1
     * @param a2 argument nr 2
     * @param a3 argument nr 3
     * @param a4 argument nr 4
     * @param a5 argument nr 5
     * @param a6 argument nr 6
     * @param a7 argument nr 7
     * @param a8 argument nr 8
     * @param a9 argument nr 9
     * @param a10 argument nr 10
     * @param a11 argument nr 11
     * @param a12 argument nr 12
     * @param a13 argument nr 13
     * @param a14 argument nr 14
     * @param a15 argument nr 15
     * @param a16 argument nr 16
     * @param a17 argument nr 17
     * @param a18 argument nr 18
     * @param a19 argument nr 19
     */
    public void accept(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10, A11 a11, A12 a12, A13 a13, A14 a14, A15 a15, A16 a16, A17 a17, A18 a18, A19 a19);
    /**
     * interface method
     * DEPRECATED - use accept(), instead
     * @param a1 argument nr 1
     * @param a2 argument nr 2
     * @param a3 argument nr 3
     * @param a4 argument nr 4
     * @param a5 argument nr 5
     * @param a6 argument nr 6
     * @param a7 argument nr 7
     * @param a8 argument nr 8
     * @param a9 argument nr 9
     * @param a10 argument nr 10
     * @param a11 argument nr 11
     * @param a12 argument nr 12
     * @param a13 argument nr 13
     * @param a14 argument nr 14
     * @param a15 argument nr 15
     * @param a16 argument nr 16
     * @param a17 argument nr 17
     * @param a18 argument nr 18
     * @param a19 argument nr 19
     */
    @Deprecated
    default public void call(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10, A11 a11, A12 a12, A13 a13, A14 a14, A15 a15, A16 a16, A17 a17, A18 a18, A19 a19) {accept(a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19);}
}