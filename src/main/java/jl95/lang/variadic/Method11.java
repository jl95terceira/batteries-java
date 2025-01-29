package jl95.lang.variadic;

/**
 * An interface for a method (a function that returns {@code void}) that takes 11 arguments. <br/>
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
 */
public interface Method11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> {

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
     */
    public void call(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10, A11 a11);
}