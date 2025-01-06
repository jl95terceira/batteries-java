package jl95terceira.lang.variadic;

/**
 * An interface for a function that takes 3 arguments. <br/>
 * @param <A1> type of argument nr 1
 * @param <A2> type of argument nr 2
 * @param <A3> type of argument nr 3
 * @param <R> type of return
 */
public interface Function3<R, A1, A2, A3> {


    /**
     * interface method
     * @param a1 argument nr 1
     * @param a2 argument nr 2
     * @param a3 argument nr 3
     * @return whatever
     */
    public R call(A1 a1, A2 a2, A3 a3);
}