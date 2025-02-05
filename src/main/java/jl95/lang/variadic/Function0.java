package jl95.lang.variadic;

/**
 * An interface for a function that takes 0 arguments. <br/>
 * 
 * @param <R> type of return
 */
public interface Function0<R> {


    /**
     * interface method
     * 
     * @return whatever
     */
    public R apply();
    /**
     * interface method
     * DEPRECATED - use apply(), instead
     * 
     * @return whatever
     */
    @Deprecated
    default public R call() { return apply();}
}