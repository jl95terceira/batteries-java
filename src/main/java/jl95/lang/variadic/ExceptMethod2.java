package jl95.lang.variadic;

/**
 * An interface for a method (a function that returns {@code void}) that takes 2 arguments. <br/>
 * @param <A1> type of argument nr 1
 * @param <A2> type of argument nr 2
 * @param <E> type of possible exception
 */
public interface ExceptMethod2<E extends Exception, A1, A2> {

    /**
     * interface method
     * @param a1 argument nr 1
     * @param a2 argument nr 2
     * @throws E generic error
     */
    public void call(A1 a1, A2 a2) throws E;
}