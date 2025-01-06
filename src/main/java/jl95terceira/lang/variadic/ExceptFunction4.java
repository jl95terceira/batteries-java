package jl95terceira.lang.variadic;

/**
 * An interface for a function that takes 4 arguments. <br/>
 * @param <A1> type of argument nr 1
 * @param <A2> type of argument nr 2
 * @param <A3> type of argument nr 3
 * @param <A4> type of argument nr 4
 * @param <R> type of return
 * @param <E> type of possible exception
 */
public interface ExceptFunction4<R, E extends Exception, A1, A2, A3, A4> {


    /**
     * interface method
     * @param a1 argument nr 1
     * @param a2 argument nr 2
     * @param a3 argument nr 3
     * @param a4 argument nr 4
     * @return whatever
     * @throws E generic error
     */
    public R call(A1 a1, A2 a2, A3 a3, A4 a4) throws E;
}