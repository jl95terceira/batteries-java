package jl95terceira.lang.variadic;

/**
 * An interface for a method (a function that returns {@code void}) that takes 2 arguments. <br/>
 * @param <A1> type of argument nr 1
 * @param <A2> type of argument nr 2
 */
public interface Method2<A1, A2> {

    /**
     * interface method
     * @param a1 argument nr 1
     * @param a2 argument nr 2
     */
    public void call(A1 a1, A2 a2);
}