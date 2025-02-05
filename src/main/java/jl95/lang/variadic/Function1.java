package jl95.lang.variadic;

/**
 * An interface for a function that takes 1 arguments. <br/>
 * @param <A1> type of argument nr 1
 * @param <R> type of return
 */
public interface Function1<R, A1> {


    /**
     * interface method
     * @param a1 argument nr 1
     * @return whatever
     */
    public R apply(A1 a1);
    /**
     * interface method
     * DEPRECATED - use apply(), instead
     * @param a1 argument nr 1
     * @return whatever
     */
    @Deprecated
    default public R call(A1 a1) { return apply(a1);}
}