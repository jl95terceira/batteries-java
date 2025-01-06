package jl95terceira.lang.variadic;

/**
 * An interface for a function that takes 0 arguments. <br/>
 * 
 * @param <R> type of return
 * @param <E> type of possible exception
 */
public interface ExceptFunction0<R, E extends Exception> {


    /**
     * interface method
     * 
     * @return whatever
     * @throws E generic error
     */
    public R call() throws E;
}