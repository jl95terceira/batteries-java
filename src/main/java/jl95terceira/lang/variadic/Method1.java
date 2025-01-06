package jl95terceira.lang.variadic;

/**
 * An interface for a method (a function that returns {@code void}) that takes 1 arguments. <br/>
 * @param <A1> type of argument nr 1
 */
public interface Method1<A1> {

    /**
     * interface method
     * @param a1 argument nr 1
     */
    public void call(A1 a1);
}