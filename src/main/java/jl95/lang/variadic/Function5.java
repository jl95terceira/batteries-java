package jl95.lang.variadic;

/**
 * An interface for a function that takes 5 arguments. <br/>
 * @param <A1> type of argument nr 1
 * @param <A2> type of argument nr 2
 * @param <A3> type of argument nr 3
 * @param <A4> type of argument nr 4
 * @param <A5> type of argument nr 5
 * @param <R> type of return
 */
public interface Function5<R, A1, A2, A3, A4, A5> {


    /**
     * interface method
     * @param a1 argument nr 1
     * @param a2 argument nr 2
     * @param a3 argument nr 3
     * @param a4 argument nr 4
     * @param a5 argument nr 5
     * @return whatever
     */
    public R call(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5);
}