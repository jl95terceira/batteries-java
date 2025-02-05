package jl95.lang.variadic;

/**
 * An interface for a method (a function that returns {@code void}) that takes 0 arguments. <br/>
 * 
 * @param <E> type of possible exception
 */
public interface ExceptMethod0<E extends Exception> {

    /**
     * interface method
     * 
     * @throws E generic error
     */
    public void accept() throws E;
    /**
     * interface method
     * DEPRECATED - use accept(), instead
     * 
     * @throws E generic error
     */
    default public void call() throws E {accept();}
}