package jl95.lang.variadic;

/**
 * An interface for a method (a function that returns {@code void}) that takes 4 arguments. <br/>
 * @param <A1> type of argument nr 1
 * @param <A2> type of argument nr 2
 * @param <A3> type of argument nr 3
 * @param <A4> type of argument nr 4
 * @param <E> type of possible exception
 */
public interface ExceptMethod4<E extends Exception, A1, A2, A3, A4> {

    /**
     * interface method
     * @param a1 argument nr 1
     * @param a2 argument nr 2
     * @param a3 argument nr 3
     * @param a4 argument nr 4
     * @throws E generic error
     */
    public void accept(A1 a1, A2 a2, A3 a3, A4 a4) throws E;
    /**
     * interface method
     * DEPRECATED - use accept(), instead
     * @param a1 argument nr 1
     * @param a2 argument nr 2
     * @param a3 argument nr 3
     * @param a4 argument nr 4
     * @throws E generic error
     */
    default public void call(A1 a1, A2 a2, A3 a3, A4 a4) throws E {accept(a1,a2,a3,a4);}
}