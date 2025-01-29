package jl95.lang.variadic;

/**
 * An interface for a method (a function that returns {@code void}) that takes 1 arguments. <br/>
 * @param <A1> type of argument nr 1
 * @param <E> type of possible exception
 */
public interface ExceptMethod1<E extends Exception, A1> {

    /**
     * interface method
     * @param a1 argument nr 1
     * @throws E generic error
     */
    public void call(A1 a1) throws E;
}