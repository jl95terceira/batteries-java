package jl95.lang.variadic;

/**
 * An interface for a method (a function that returns {@code void}) that takes 1 arguments. <br/>
 * @param <A1> type of argument nr 1
 */
public interface Method1<A1> {

    /**
     * interface method
     * @param a1 argument nr 1
     */
    public void accept(A1 a1);
    /**
     * interface method
     * DEPRECATED - use accept(), instead
     * @param a1 argument nr 1
     */
    @Deprecated
    default public void call(A1 a1) {accept(a1);}
}