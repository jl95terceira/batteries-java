package jl95terceira.lang.variadic;

/**
 * An interface for a function that takes 1 arguments. <br/>
 * @param <A1> type of argument nr 1
 * @param <R> type of return
 * @param <E> type of possible exception
 */
public interface ExceptFunction1<R, E extends Exception, A1> {


    /**
     * interface method
     * @param a1 argument nr 1
     * @return whatever
     * @throws E generic error
     */
    public R call(A1 a1) throws E;
}