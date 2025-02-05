package jl95.lang.variadic;

/**
 * An interface for a method (a function that returns {@code void}) that takes 3 arguments. <br/>
 * @param <A1> type of argument nr 1
 * @param <A2> type of argument nr 2
 * @param <A3> type of argument nr 3
 */
public interface Method3<A1, A2, A3> {

    /**
     * interface method
     * @param a1 argument nr 1
     * @param a2 argument nr 2
     * @param a3 argument nr 3
     */
    public void accept(A1 a1, A2 a2, A3 a3);
    /**
     * interface method
     * DEPRECATED - use accept(), instead
     * @param a1 argument nr 1
     * @param a2 argument nr 2
     * @param a3 argument nr 3
     */
    @Deprecated
    default public void call(A1 a1, A2 a2, A3 a3) {accept(a1,a2,a3);}
}