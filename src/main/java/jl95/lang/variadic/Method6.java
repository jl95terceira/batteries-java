package jl95.lang.variadic;

/**
 * An interface for a method (a function that returns {@code void}) that takes 6 arguments. <br/>
 * @param <A1> type of argument nr 1
 * @param <A2> type of argument nr 2
 * @param <A3> type of argument nr 3
 * @param <A4> type of argument nr 4
 * @param <A5> type of argument nr 5
 * @param <A6> type of argument nr 6
 */
public interface Method6<A1, A2, A3, A4, A5, A6> {

    /**
     * interface method
     * @param a1 argument nr 1
     * @param a2 argument nr 2
     * @param a3 argument nr 3
     * @param a4 argument nr 4
     * @param a5 argument nr 5
     * @param a6 argument nr 6
     */
    public void accept(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6);
    /**
     * interface method
     * DEPRECATED - use accept(), instead
     * @param a1 argument nr 1
     * @param a2 argument nr 2
     * @param a3 argument nr 3
     * @param a4 argument nr 4
     * @param a5 argument nr 5
     * @param a6 argument nr 6
     */
    @Deprecated
    default public void call(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6) {accept(a1,a2,a3,a4,a5,a6);}
}