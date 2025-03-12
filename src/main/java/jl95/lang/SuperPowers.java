package jl95.lang;

import jl95.lang.variadic.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.function.BooleanSupplier;

/**
 * A collection of classes and of methods to extend the Java language itself, to get more stuff done with less code. <br/>
 * <br/>
 * Paste the following line of code into your classes<br/>
 * <pre>{@code import static jl95.lang.SuperPowers.*;}</pre>
 * to call the methods directly like {@code strip(...)}, {@code ifNull(..., ...)}, etc.
 * Paste
 * <pre>{@code import jl95.lang.SuperPowers.*;}</pre>
 * to use static method reference syntax in more functional styles like {@code SuperPowers::strip}, {@code SuperPowers::ifNull}, etc.
 */
public class SuperPowers {

    private static Boolean SHUTTING_DOWN_FLAG = false;

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> { SHUTTING_DOWN_FLAG = true; }));
    }

    private SuperPowers() {}
    
    /**
     * Error thrown when sleep is interrupted from within {@link #sleep(java.lang.Long)} / {@link #sleep(java.lang.Long, java.util.function.BooleanSupplier, java.lang.Long)}
     */
    public static class SleepError extends RuntimeException { private SleepError() { super(); }}

    public static Boolean SHUTTING_DOWN() { return SHUTTING_DOWN_FLAG; }
    /*** ITERATION ***/
    /**
     * convenient static method to turn an array /varargs into a super-powered iterable
     * @param <T> element type
     * @param xx array / varargs
     * @return iterable with super-powers
     */
    public static <T>    
                  I<T>          I    (T...                   xx) { return I.ofArray(xx); }
    /**
     * convenient static method to make a (array) list from the given elements
     * @param <T> element type
     * @param xx elements
     * @return list
     */
    public static <T>
                  ArrayList<T>  List (T...                   xx) {
                      
        return I(xx).to(new java.util.ArrayList<>(xx.length));
    }
    /**
     * convenient static method to make a (array) list from the given elements
     * @param <T> element type
     * @param xx elements
     * @return list
     */
    public static <T>
                  ArrayList<T>  ListOf(Iterable<T>           xx) {
                      
        return I.of(xx).to(new java.util.ArrayList<>());
    }
    /**
     * convenient static method to make a (linked) list from the given elements
     * @param <T> element type
     * @param xx elements
     * @return list
     */
    public static <T>
                  LinkedList<T> LinkedList (T...                   xx) {

        return I(xx).to(new java.util.LinkedList<>());
    }
    /**
     * convenient static method to make a (linked) list from the given elements
     * @param <T> element type
     * @param xx elements
     * @return list
     */
    public static <T>
                  LinkedList<T> LinkedListOf(Iterable<T>           xx) {

        return I.of(xx).to(new java.util.LinkedList<>());
    }
    /**
     * convenient static method to make a (hash) set from the given elements
     * @param <T> element type
     * @param xx elements
     * @return list
     */
    public static <T>
                  HashSet <T>   Set  (T...                   xx) {
                      
        return I(xx).to(new java.util.HashSet<>(xx.length));
    }
    /**
     * convenient static method to make a (hash) set from the given elements
     * @param <T> element type
     * @param xx elements
     * @return list
     */
    public static <T>
                  HashSet <T>   SetOf(Iterable<T>            xx) {
                      
        return I.of(xx).to(new java.util.HashSet<>());
    }
    /**
     * convenient static method to make a (hash) map from the given elements as key-value pairs
     * @param <K> key type
     * @param <V> value type
     * @param xx elements as key-value pairs
     * @return map
     */
    public static <K, V>
                  HashMap<K, V> Map  (Tuple2<K, V>...        xx) {
        
        return I(xx).to(new java.util.HashMap<>(xx.length), x -> x.a1, x -> x.a2);
    }
    /**
     * convenient static method to make a (hash) map from the given elements as key-value pairs
     * @param <K> key type
     * @param <V> value type
     * @param xx elements as key-value pairs
     * @return map
     */
    public static <K, V>
                  HashMap<K, V> MapOf(Iterable<Tuple2<K, V>> xx) {
        
        return I.of(xx).to(new java.util.HashMap<>(), x -> x.a1, x -> x.a2);
    }
    /**
     * sleep for a duration
     * @param period duration (milliseconds)
     * @see java.lang.Thread#sleep(long)
     */
    public static void          sleep(Long      period) {
        
        try { Thread.sleep(period); }
        catch (java.lang.InterruptedException exc) { throw new SuperPowers.SleepError(); }
    }
    /**
     * same as {@link SuperPowers#sleep(Long)} with period of type {@link Integer}
     */
    public static void          sleep(Integer   period) {

        sleep(period.longValue());
    }
    /**
     * sleep for a duration, with the possibility of interrupting early
     * @param period duration
     * @param stopEarly supplier of whether to interrupt
     * @param periodPartial sub-duration within sleep duration after every which to check whether to stop early
     * @return whether slept for the full duration (no early interruption)
     */
    public static Boolean       sleep(Long      period,
                                      java.util.function.BooleanSupplier 
                                                stopEarly,
                                      Long      periodPartial) {
        
        for (Integer i: I.range(Long.valueOf(period / periodPartial).intValue())) {
            
            sleep(periodPartial);
            if (stopEarly.getAsBoolean()) return false;
        }
        sleep(period % periodPartial);
        return true;
    }
    /**
     * same as {@link SuperPowers#sleep(Long, BooleanSupplier, Long)} with period of type {@link Integer}
     */
    public static Boolean       sleep(Integer   period,
                                      java.util.function.BooleanSupplier
                                                stopEarly,
                                      Long      periodPartial) {

        return sleep(period.longValue(), stopEarly, periodPartial);
    }
    @FunctionalInterface
    public interface            SelfInterface {
        <T> T apply(T x);
    }
    public static <T> T         self     (T      x) { return x; }
    public static SelfInterface self     = SuperPowers::self;
    @FunctionalInterface
    public interface            ConstantInterface {
        <T> Function0<T> apply(T x);
    }
    public static <T>
                  Function0<T>  constant (T      x) { return () -> x; }
    public static ConstantInterface constant = SuperPowers::constant;
    @FunctionalInterface
    public interface            IsBlankInterface {
        Boolean apply(String s);
    }
    public static Boolean       isBlank  (String s) {
        
        /* SWITCH:17 */
        return s.isBlank();
        /* END */
        /* SWITCH:8 */
//OFF:        for (char c: s.toCharArray()) {
            
//OFF:            if (!_s.contains(c)) { return false; }
//OFF:        }
//OFF:        return true;
        /* END */
    }
    public static IsBlankInterface isBlank = SuperPowers::isBlank;
    @FunctionalInterface
    public interface            StripInterface extends Function1<String, String> {}
    public static String        strip    (String s) {
        
        /* SWITCH:17 */
        return s.strip();
        /* END */
        /* SWITCH:8 */
//OFF:        return org.apache.commons.lang3.StringUtils.strip(s);
        /* END */
    }
    public static StripInterface strip = SuperPowers::strip;
    @FunctionalInterface
    public interface            RepeatInterface extends Function2<String, String, Integer> {}
    public static String        repeat   (String s, Integer n) {
        
        /* SWITCH:17 */
        return s.repeat(n);
        /* END */
        /* SWITCH:8 */
//OFF:        return org.apache.commons.lang3.StringUtils.repeat(s, n);
        /* END */
    }
    public static RepeatInterface repeat = SuperPowers::repeat;
    @FunctionalInterface
    public interface            IfNullInterface {
        <T> T apply(T x, T fallback);
    }
    public static <T> T         ifNull   (T x, T fallback) { return x != null? x: fallback; }
    public static IfNullInterface ifNull = SuperPowers::ifNull;
    public static <T, E extends Exception>
                  Function0<T>  unchecked(ExceptFunction0<T, E> f) {
        return () -> {
            try {
                return f.call();
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }
    public static <T, E extends Exception>
                  T             uncheck  (ExceptFunction0<T, E> f) { return unchecked(f).call(); }
    public static <E extends Exception>
                  Method0       unchecked(ExceptMethod0<E> f) {
        return () -> {
            try {
                f.call();
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }
    public static <E extends Exception>
                  void          uncheck  (ExceptMethod0<E> f) { unchecked(f).call(); }
    public static <K, V>
                  StrictMap<K, V> strict(java.util.Map<K, V> x) { return StrictMap.of(x); }
    public static <T>
                  StrictSet<T>    strict(java.util.Set<T>    x) { return StrictSet.of(x); }

    /*PPJAVA

N = 20
for i in range(N):
    
    gens = ('<{0}>'.format(', '.join('A{0}'.format(j) for j in range(1,1+i)))) if i > 0 else ''
    writelines(('    \/**',
                '     * ',
                f'     * tuple of {i} elements',
                '    ',
                *(f'     * @param <A{j}> type {j}' for j in range(1,1+i)),
                *(f'     * @param a{j} value {j}' for j in range(1,1+i)),
                '     * @return tuple',
                '     *\/',
                '    public static {gens} Tuple{i}{gens} tuple({aas}) {{ return new Tuple{i}{diamond}({aa}); }}'.format(i=i, gens=gens, diamond='<>' if i > 0 else '', aa=', '.join(f'a{j}' for j in range(1,1+i)), aas=', '.join(f'A{j} a{j}' for j in range(1,1+i))),))

for i in range(N):
    
    gens = ('<R, {0}>'.format(', '.join('A{0}'.format(j) for j in range(1,1+i)))) if i > 0 else '<R>'
    writelines(('    \/**',
                '     * ',
                f'     * function with {i} arguments',
                '    ',
                '     * @param f function itself (e.g. as a lambda)',
                *(f'     * @param <A{j}> argument type {j}' for j in range(1,1+i)),
                '     * @param <R> return type',
                '     * @return function itself',
                '     *\/',
                '    public static {gens} Function{i}{gens} function(Function{i}{gens} f) {{ return f; }}'.format(i=i, gens=gens),))

for i in range(N):
    
    gensr = ('<R, E extends Exception, {0}>'.format(', '.join('A{0}'.format(j) for j in range(1,1+i)))) if i > 0 else '<R, E extends Exception>'
    gens  = ('<R, E, {0}>'                  .format(', '.join('A{0}'.format(j) for j in range(1,1+i)))) if i > 0 else '<R, E>'
    writelines(('    \/**',
                '     * ',
                f'     * function with {i} arguments, that declares to throw an exception',
                '    ',
                '     * @param f function itself (e.g. as a lambda)',
                '     * @param <E> exception type',
                *(f'     * @param <A{j}> argument type {j}' for j in range(1,1+i)),
                '     * @param <R> return type',
                '     * @return function itself',
                '     *\/',
                '    public static {gensr} ExceptFunction{i}{gens} exfunction(ExceptFunction{i}{gens} f) {{ return f; }}'.format(i=i, gens=gens, gensr=gensr),))

for i in range(N):
    
    gens = ('<{0}>'.format(', '.join('A{0}'.format(j) for j in range(1,1+i)))) if i > 0 else ''
    writelines(('    \/**',
                '     * ',
                f'     * method (void-returning function) with {i} arguments',
                '    ',
                '     * @param f method itself (e.g. as a lambda)',
                *(f'     * @param <A{j}> argument type {j}' for j in range(1,1+i)),
                '     * @return method',
                '     *\/',
                '    public static {gens} Method{i}{gens} method(Method{i}{gens} f) {{ return f; }}'.format(i=i, gens=gens),))
for i in range(N):
    
    gens  = ('<E, {0}>'                  .format(', '.join('A{0}'.format(j) for j in range(1,1+i)))) if i > 0 else '<E>'
    gensr = ('<E extends Exception, {0}>'.format(', '.join('A{0}'.format(j) for j in range(1,1+i)))) if i > 0 else '<E extends Exception>'
    writelines(('    \/**',
                '     * ',
                f'     * method (void-returning function) with {i} arguments, that declares to throw an exception',
                '    ',
                '     * @param f method itself (e.g. as a lambda)',
                '     * @param <E> exception type',
                *(f'     * @param <A{j}> argument type {j}' for j in range(1,1+i)),
                '     * @return method',
                '     *\/',
                '    public static {gensr} ExceptMethod{i}{gens} exmethod(ExceptMethod{i}{gens} f) {{ return f; }}'.format(i=i, gens=gens, gensr=gensr),))

    */
    /**
     * 
     * tuple of 0 elements
    
     * @return tuple
     */
    public static  Tuple0 tuple() { return new Tuple0(); }
    /**
     * 
     * tuple of 1 elements
    
     * @param <A1> type 1
     * @param a1 value 1
     * @return tuple
     */
    public static <A1> Tuple1<A1> tuple(A1 a1) { return new Tuple1<>(a1); }
    /**
     * 
     * tuple of 2 elements
    
     * @param <A1> type 1
     * @param <A2> type 2
     * @param a1 value 1
     * @param a2 value 2
     * @return tuple
     */
    public static <A1, A2> Tuple2<A1, A2> tuple(A1 a1, A2 a2) { return new Tuple2<>(a1, a2); }
    /**
     * 
     * tuple of 3 elements
    
     * @param <A1> type 1
     * @param <A2> type 2
     * @param <A3> type 3
     * @param a1 value 1
     * @param a2 value 2
     * @param a3 value 3
     * @return tuple
     */
    public static <A1, A2, A3> Tuple3<A1, A2, A3> tuple(A1 a1, A2 a2, A3 a3) { return new Tuple3<>(a1, a2, a3); }
    /**
     * 
     * tuple of 4 elements
    
     * @param <A1> type 1
     * @param <A2> type 2
     * @param <A3> type 3
     * @param <A4> type 4
     * @param a1 value 1
     * @param a2 value 2
     * @param a3 value 3
     * @param a4 value 4
     * @return tuple
     */
    public static <A1, A2, A3, A4> Tuple4<A1, A2, A3, A4> tuple(A1 a1, A2 a2, A3 a3, A4 a4) { return new Tuple4<>(a1, a2, a3, a4); }
    /**
     * 
     * tuple of 5 elements
    
     * @param <A1> type 1
     * @param <A2> type 2
     * @param <A3> type 3
     * @param <A4> type 4
     * @param <A5> type 5
     * @param a1 value 1
     * @param a2 value 2
     * @param a3 value 3
     * @param a4 value 4
     * @param a5 value 5
     * @return tuple
     */
    public static <A1, A2, A3, A4, A5> Tuple5<A1, A2, A3, A4, A5> tuple(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5) { return new Tuple5<>(a1, a2, a3, a4, a5); }
    /**
     * 
     * tuple of 6 elements
    
     * @param <A1> type 1
     * @param <A2> type 2
     * @param <A3> type 3
     * @param <A4> type 4
     * @param <A5> type 5
     * @param <A6> type 6
     * @param a1 value 1
     * @param a2 value 2
     * @param a3 value 3
     * @param a4 value 4
     * @param a5 value 5
     * @param a6 value 6
     * @return tuple
     */
    public static <A1, A2, A3, A4, A5, A6> Tuple6<A1, A2, A3, A4, A5, A6> tuple(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6) { return new Tuple6<>(a1, a2, a3, a4, a5, a6); }
    /**
     * 
     * tuple of 7 elements
    
     * @param <A1> type 1
     * @param <A2> type 2
     * @param <A3> type 3
     * @param <A4> type 4
     * @param <A5> type 5
     * @param <A6> type 6
     * @param <A7> type 7
     * @param a1 value 1
     * @param a2 value 2
     * @param a3 value 3
     * @param a4 value 4
     * @param a5 value 5
     * @param a6 value 6
     * @param a7 value 7
     * @return tuple
     */
    public static <A1, A2, A3, A4, A5, A6, A7> Tuple7<A1, A2, A3, A4, A5, A6, A7> tuple(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7) { return new Tuple7<>(a1, a2, a3, a4, a5, a6, a7); }
    /**
     * 
     * tuple of 8 elements
    
     * @param <A1> type 1
     * @param <A2> type 2
     * @param <A3> type 3
     * @param <A4> type 4
     * @param <A5> type 5
     * @param <A6> type 6
     * @param <A7> type 7
     * @param <A8> type 8
     * @param a1 value 1
     * @param a2 value 2
     * @param a3 value 3
     * @param a4 value 4
     * @param a5 value 5
     * @param a6 value 6
     * @param a7 value 7
     * @param a8 value 8
     * @return tuple
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8> Tuple8<A1, A2, A3, A4, A5, A6, A7, A8> tuple(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8) { return new Tuple8<>(a1, a2, a3, a4, a5, a6, a7, a8); }
    /**
     * 
     * tuple of 9 elements
    
     * @param <A1> type 1
     * @param <A2> type 2
     * @param <A3> type 3
     * @param <A4> type 4
     * @param <A5> type 5
     * @param <A6> type 6
     * @param <A7> type 7
     * @param <A8> type 8
     * @param <A9> type 9
     * @param a1 value 1
     * @param a2 value 2
     * @param a3 value 3
     * @param a4 value 4
     * @param a5 value 5
     * @param a6 value 6
     * @param a7 value 7
     * @param a8 value 8
     * @param a9 value 9
     * @return tuple
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9> Tuple9<A1, A2, A3, A4, A5, A6, A7, A8, A9> tuple(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9) { return new Tuple9<>(a1, a2, a3, a4, a5, a6, a7, a8, a9); }
    /**
     * 
     * tuple of 10 elements
    
     * @param <A1> type 1
     * @param <A2> type 2
     * @param <A3> type 3
     * @param <A4> type 4
     * @param <A5> type 5
     * @param <A6> type 6
     * @param <A7> type 7
     * @param <A8> type 8
     * @param <A9> type 9
     * @param <A10> type 10
     * @param a1 value 1
     * @param a2 value 2
     * @param a3 value 3
     * @param a4 value 4
     * @param a5 value 5
     * @param a6 value 6
     * @param a7 value 7
     * @param a8 value 8
     * @param a9 value 9
     * @param a10 value 10
     * @return tuple
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> Tuple10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> tuple(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10) { return new Tuple10<>(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10); }
    /**
     * 
     * tuple of 11 elements
    
     * @param <A1> type 1
     * @param <A2> type 2
     * @param <A3> type 3
     * @param <A4> type 4
     * @param <A5> type 5
     * @param <A6> type 6
     * @param <A7> type 7
     * @param <A8> type 8
     * @param <A9> type 9
     * @param <A10> type 10
     * @param <A11> type 11
     * @param a1 value 1
     * @param a2 value 2
     * @param a3 value 3
     * @param a4 value 4
     * @param a5 value 5
     * @param a6 value 6
     * @param a7 value 7
     * @param a8 value 8
     * @param a9 value 9
     * @param a10 value 10
     * @param a11 value 11
     * @return tuple
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> Tuple11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> tuple(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10, A11 a11) { return new Tuple11<>(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11); }
    /**
     * 
     * tuple of 12 elements
    
     * @param <A1> type 1
     * @param <A2> type 2
     * @param <A3> type 3
     * @param <A4> type 4
     * @param <A5> type 5
     * @param <A6> type 6
     * @param <A7> type 7
     * @param <A8> type 8
     * @param <A9> type 9
     * @param <A10> type 10
     * @param <A11> type 11
     * @param <A12> type 12
     * @param a1 value 1
     * @param a2 value 2
     * @param a3 value 3
     * @param a4 value 4
     * @param a5 value 5
     * @param a6 value 6
     * @param a7 value 7
     * @param a8 value 8
     * @param a9 value 9
     * @param a10 value 10
     * @param a11 value 11
     * @param a12 value 12
     * @return tuple
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> Tuple12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> tuple(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10, A11 a11, A12 a12) { return new Tuple12<>(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12); }
    /**
     * 
     * tuple of 13 elements
    
     * @param <A1> type 1
     * @param <A2> type 2
     * @param <A3> type 3
     * @param <A4> type 4
     * @param <A5> type 5
     * @param <A6> type 6
     * @param <A7> type 7
     * @param <A8> type 8
     * @param <A9> type 9
     * @param <A10> type 10
     * @param <A11> type 11
     * @param <A12> type 12
     * @param <A13> type 13
     * @param a1 value 1
     * @param a2 value 2
     * @param a3 value 3
     * @param a4 value 4
     * @param a5 value 5
     * @param a6 value 6
     * @param a7 value 7
     * @param a8 value 8
     * @param a9 value 9
     * @param a10 value 10
     * @param a11 value 11
     * @param a12 value 12
     * @param a13 value 13
     * @return tuple
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> Tuple13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> tuple(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10, A11 a11, A12 a12, A13 a13) { return new Tuple13<>(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13); }
    /**
     * 
     * tuple of 14 elements
    
     * @param <A1> type 1
     * @param <A2> type 2
     * @param <A3> type 3
     * @param <A4> type 4
     * @param <A5> type 5
     * @param <A6> type 6
     * @param <A7> type 7
     * @param <A8> type 8
     * @param <A9> type 9
     * @param <A10> type 10
     * @param <A11> type 11
     * @param <A12> type 12
     * @param <A13> type 13
     * @param <A14> type 14
     * @param a1 value 1
     * @param a2 value 2
     * @param a3 value 3
     * @param a4 value 4
     * @param a5 value 5
     * @param a6 value 6
     * @param a7 value 7
     * @param a8 value 8
     * @param a9 value 9
     * @param a10 value 10
     * @param a11 value 11
     * @param a12 value 12
     * @param a13 value 13
     * @param a14 value 14
     * @return tuple
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> Tuple14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> tuple(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10, A11 a11, A12 a12, A13 a13, A14 a14) { return new Tuple14<>(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14); }
    /**
     * 
     * tuple of 15 elements
    
     * @param <A1> type 1
     * @param <A2> type 2
     * @param <A3> type 3
     * @param <A4> type 4
     * @param <A5> type 5
     * @param <A6> type 6
     * @param <A7> type 7
     * @param <A8> type 8
     * @param <A9> type 9
     * @param <A10> type 10
     * @param <A11> type 11
     * @param <A12> type 12
     * @param <A13> type 13
     * @param <A14> type 14
     * @param <A15> type 15
     * @param a1 value 1
     * @param a2 value 2
     * @param a3 value 3
     * @param a4 value 4
     * @param a5 value 5
     * @param a6 value 6
     * @param a7 value 7
     * @param a8 value 8
     * @param a9 value 9
     * @param a10 value 10
     * @param a11 value 11
     * @param a12 value 12
     * @param a13 value 13
     * @param a14 value 14
     * @param a15 value 15
     * @return tuple
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> Tuple15<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> tuple(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10, A11 a11, A12 a12, A13 a13, A14 a14, A15 a15) { return new Tuple15<>(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15); }
    /**
     * 
     * tuple of 16 elements
    
     * @param <A1> type 1
     * @param <A2> type 2
     * @param <A3> type 3
     * @param <A4> type 4
     * @param <A5> type 5
     * @param <A6> type 6
     * @param <A7> type 7
     * @param <A8> type 8
     * @param <A9> type 9
     * @param <A10> type 10
     * @param <A11> type 11
     * @param <A12> type 12
     * @param <A13> type 13
     * @param <A14> type 14
     * @param <A15> type 15
     * @param <A16> type 16
     * @param a1 value 1
     * @param a2 value 2
     * @param a3 value 3
     * @param a4 value 4
     * @param a5 value 5
     * @param a6 value 6
     * @param a7 value 7
     * @param a8 value 8
     * @param a9 value 9
     * @param a10 value 10
     * @param a11 value 11
     * @param a12 value 12
     * @param a13 value 13
     * @param a14 value 14
     * @param a15 value 15
     * @param a16 value 16
     * @return tuple
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16> Tuple16<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16> tuple(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10, A11 a11, A12 a12, A13 a13, A14 a14, A15 a15, A16 a16) { return new Tuple16<>(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16); }
    /**
     * 
     * tuple of 17 elements
    
     * @param <A1> type 1
     * @param <A2> type 2
     * @param <A3> type 3
     * @param <A4> type 4
     * @param <A5> type 5
     * @param <A6> type 6
     * @param <A7> type 7
     * @param <A8> type 8
     * @param <A9> type 9
     * @param <A10> type 10
     * @param <A11> type 11
     * @param <A12> type 12
     * @param <A13> type 13
     * @param <A14> type 14
     * @param <A15> type 15
     * @param <A16> type 16
     * @param <A17> type 17
     * @param a1 value 1
     * @param a2 value 2
     * @param a3 value 3
     * @param a4 value 4
     * @param a5 value 5
     * @param a6 value 6
     * @param a7 value 7
     * @param a8 value 8
     * @param a9 value 9
     * @param a10 value 10
     * @param a11 value 11
     * @param a12 value 12
     * @param a13 value 13
     * @param a14 value 14
     * @param a15 value 15
     * @param a16 value 16
     * @param a17 value 17
     * @return tuple
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17> Tuple17<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17> tuple(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10, A11 a11, A12 a12, A13 a13, A14 a14, A15 a15, A16 a16, A17 a17) { return new Tuple17<>(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17); }
    /**
     * 
     * tuple of 18 elements
    
     * @param <A1> type 1
     * @param <A2> type 2
     * @param <A3> type 3
     * @param <A4> type 4
     * @param <A5> type 5
     * @param <A6> type 6
     * @param <A7> type 7
     * @param <A8> type 8
     * @param <A9> type 9
     * @param <A10> type 10
     * @param <A11> type 11
     * @param <A12> type 12
     * @param <A13> type 13
     * @param <A14> type 14
     * @param <A15> type 15
     * @param <A16> type 16
     * @param <A17> type 17
     * @param <A18> type 18
     * @param a1 value 1
     * @param a2 value 2
     * @param a3 value 3
     * @param a4 value 4
     * @param a5 value 5
     * @param a6 value 6
     * @param a7 value 7
     * @param a8 value 8
     * @param a9 value 9
     * @param a10 value 10
     * @param a11 value 11
     * @param a12 value 12
     * @param a13 value 13
     * @param a14 value 14
     * @param a15 value 15
     * @param a16 value 16
     * @param a17 value 17
     * @param a18 value 18
     * @return tuple
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18> Tuple18<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18> tuple(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10, A11 a11, A12 a12, A13 a13, A14 a14, A15 a15, A16 a16, A17 a17, A18 a18) { return new Tuple18<>(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18); }
    /**
     * 
     * tuple of 19 elements
    
     * @param <A1> type 1
     * @param <A2> type 2
     * @param <A3> type 3
     * @param <A4> type 4
     * @param <A5> type 5
     * @param <A6> type 6
     * @param <A7> type 7
     * @param <A8> type 8
     * @param <A9> type 9
     * @param <A10> type 10
     * @param <A11> type 11
     * @param <A12> type 12
     * @param <A13> type 13
     * @param <A14> type 14
     * @param <A15> type 15
     * @param <A16> type 16
     * @param <A17> type 17
     * @param <A18> type 18
     * @param <A19> type 19
     * @param a1 value 1
     * @param a2 value 2
     * @param a3 value 3
     * @param a4 value 4
     * @param a5 value 5
     * @param a6 value 6
     * @param a7 value 7
     * @param a8 value 8
     * @param a9 value 9
     * @param a10 value 10
     * @param a11 value 11
     * @param a12 value 12
     * @param a13 value 13
     * @param a14 value 14
     * @param a15 value 15
     * @param a16 value 16
     * @param a17 value 17
     * @param a18 value 18
     * @param a19 value 19
     * @return tuple
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19> Tuple19<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19> tuple(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10, A11 a11, A12 a12, A13 a13, A14 a14, A15 a15, A16 a16, A17 a17, A18 a18, A19 a19) { return new Tuple19<>(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19); }
    /**
     * 
     * function with 0 arguments
    
     * @param f function itself (e.g. as a lambda)
     * @param <R> return type
     * @return function itself
     */
    public static <R> Function0<R> function(Function0<R> f) { return f; }
    /**
     * 
     * function with 1 arguments
    
     * @param f function itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <R> return type
     * @return function itself
     */
    public static <R, A1> Function1<R, A1> function(Function1<R, A1> f) { return f; }
    /**
     * 
     * function with 2 arguments
    
     * @param f function itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <R> return type
     * @return function itself
     */
    public static <R, A1, A2> Function2<R, A1, A2> function(Function2<R, A1, A2> f) { return f; }
    /**
     * 
     * function with 3 arguments
    
     * @param f function itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <R> return type
     * @return function itself
     */
    public static <R, A1, A2, A3> Function3<R, A1, A2, A3> function(Function3<R, A1, A2, A3> f) { return f; }
    /**
     * 
     * function with 4 arguments
    
     * @param f function itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <R> return type
     * @return function itself
     */
    public static <R, A1, A2, A3, A4> Function4<R, A1, A2, A3, A4> function(Function4<R, A1, A2, A3, A4> f) { return f; }
    /**
     * 
     * function with 5 arguments
    
     * @param f function itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <R> return type
     * @return function itself
     */
    public static <R, A1, A2, A3, A4, A5> Function5<R, A1, A2, A3, A4, A5> function(Function5<R, A1, A2, A3, A4, A5> f) { return f; }
    /**
     * 
     * function with 6 arguments
    
     * @param f function itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <R> return type
     * @return function itself
     */
    public static <R, A1, A2, A3, A4, A5, A6> Function6<R, A1, A2, A3, A4, A5, A6> function(Function6<R, A1, A2, A3, A4, A5, A6> f) { return f; }
    /**
     * 
     * function with 7 arguments
    
     * @param f function itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <R> return type
     * @return function itself
     */
    public static <R, A1, A2, A3, A4, A5, A6, A7> Function7<R, A1, A2, A3, A4, A5, A6, A7> function(Function7<R, A1, A2, A3, A4, A5, A6, A7> f) { return f; }
    /**
     * 
     * function with 8 arguments
    
     * @param f function itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <R> return type
     * @return function itself
     */
    public static <R, A1, A2, A3, A4, A5, A6, A7, A8> Function8<R, A1, A2, A3, A4, A5, A6, A7, A8> function(Function8<R, A1, A2, A3, A4, A5, A6, A7, A8> f) { return f; }
    /**
     * 
     * function with 9 arguments
    
     * @param f function itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <R> return type
     * @return function itself
     */
    public static <R, A1, A2, A3, A4, A5, A6, A7, A8, A9> Function9<R, A1, A2, A3, A4, A5, A6, A7, A8, A9> function(Function9<R, A1, A2, A3, A4, A5, A6, A7, A8, A9> f) { return f; }
    /**
     * 
     * function with 10 arguments
    
     * @param f function itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <R> return type
     * @return function itself
     */
    public static <R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> Function10<R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> function(Function10<R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> f) { return f; }
    /**
     * 
     * function with 11 arguments
    
     * @param f function itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <R> return type
     * @return function itself
     */
    public static <R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> Function11<R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> function(Function11<R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> f) { return f; }
    /**
     * 
     * function with 12 arguments
    
     * @param f function itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <R> return type
     * @return function itself
     */
    public static <R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> Function12<R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> function(Function12<R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> f) { return f; }
    /**
     * 
     * function with 13 arguments
    
     * @param f function itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <R> return type
     * @return function itself
     */
    public static <R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> Function13<R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> function(Function13<R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> f) { return f; }
    /**
     * 
     * function with 14 arguments
    
     * @param f function itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @param <R> return type
     * @return function itself
     */
    public static <R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> Function14<R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> function(Function14<R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> f) { return f; }
    /**
     * 
     * function with 15 arguments
    
     * @param f function itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @param <A15> argument type 15
     * @param <R> return type
     * @return function itself
     */
    public static <R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> Function15<R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> function(Function15<R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> f) { return f; }
    /**
     * 
     * function with 16 arguments
    
     * @param f function itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @param <A15> argument type 15
     * @param <A16> argument type 16
     * @param <R> return type
     * @return function itself
     */
    public static <R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16> Function16<R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16> function(Function16<R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16> f) { return f; }
    /**
     * 
     * function with 17 arguments
    
     * @param f function itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @param <A15> argument type 15
     * @param <A16> argument type 16
     * @param <A17> argument type 17
     * @param <R> return type
     * @return function itself
     */
    public static <R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17> Function17<R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17> function(Function17<R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17> f) { return f; }
    /**
     * 
     * function with 18 arguments
    
     * @param f function itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @param <A15> argument type 15
     * @param <A16> argument type 16
     * @param <A17> argument type 17
     * @param <A18> argument type 18
     * @param <R> return type
     * @return function itself
     */
    public static <R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18> Function18<R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18> function(Function18<R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18> f) { return f; }
    /**
     * 
     * function with 19 arguments
    
     * @param f function itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @param <A15> argument type 15
     * @param <A16> argument type 16
     * @param <A17> argument type 17
     * @param <A18> argument type 18
     * @param <A19> argument type 19
     * @param <R> return type
     * @return function itself
     */
    public static <R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19> Function19<R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19> function(Function19<R, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19> f) { return f; }
    /**
     * 
     * function with 0 arguments, that declares to throw an exception
    
     * @param f function itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <R> return type
     * @return function itself
     */
    public static <R, E extends Exception> ExceptFunction0<R, E> exfunction(ExceptFunction0<R, E> f) { return f; }
    /**
     * 
     * function with 1 arguments, that declares to throw an exception
    
     * @param f function itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <R> return type
     * @return function itself
     */
    public static <R, E extends Exception, A1> ExceptFunction1<R, E, A1> exfunction(ExceptFunction1<R, E, A1> f) { return f; }
    /**
     * 
     * function with 2 arguments, that declares to throw an exception
    
     * @param f function itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <R> return type
     * @return function itself
     */
    public static <R, E extends Exception, A1, A2> ExceptFunction2<R, E, A1, A2> exfunction(ExceptFunction2<R, E, A1, A2> f) { return f; }
    /**
     * 
     * function with 3 arguments, that declares to throw an exception
    
     * @param f function itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <R> return type
     * @return function itself
     */
    public static <R, E extends Exception, A1, A2, A3> ExceptFunction3<R, E, A1, A2, A3> exfunction(ExceptFunction3<R, E, A1, A2, A3> f) { return f; }
    /**
     * 
     * function with 4 arguments, that declares to throw an exception
    
     * @param f function itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <R> return type
     * @return function itself
     */
    public static <R, E extends Exception, A1, A2, A3, A4> ExceptFunction4<R, E, A1, A2, A3, A4> exfunction(ExceptFunction4<R, E, A1, A2, A3, A4> f) { return f; }
    /**
     * 
     * function with 5 arguments, that declares to throw an exception
    
     * @param f function itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <R> return type
     * @return function itself
     */
    public static <R, E extends Exception, A1, A2, A3, A4, A5> ExceptFunction5<R, E, A1, A2, A3, A4, A5> exfunction(ExceptFunction5<R, E, A1, A2, A3, A4, A5> f) { return f; }
    /**
     * 
     * function with 6 arguments, that declares to throw an exception
    
     * @param f function itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <R> return type
     * @return function itself
     */
    public static <R, E extends Exception, A1, A2, A3, A4, A5, A6> ExceptFunction6<R, E, A1, A2, A3, A4, A5, A6> exfunction(ExceptFunction6<R, E, A1, A2, A3, A4, A5, A6> f) { return f; }
    /**
     * 
     * function with 7 arguments, that declares to throw an exception
    
     * @param f function itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <R> return type
     * @return function itself
     */
    public static <R, E extends Exception, A1, A2, A3, A4, A5, A6, A7> ExceptFunction7<R, E, A1, A2, A3, A4, A5, A6, A7> exfunction(ExceptFunction7<R, E, A1, A2, A3, A4, A5, A6, A7> f) { return f; }
    /**
     * 
     * function with 8 arguments, that declares to throw an exception
    
     * @param f function itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <R> return type
     * @return function itself
     */
    public static <R, E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8> ExceptFunction8<R, E, A1, A2, A3, A4, A5, A6, A7, A8> exfunction(ExceptFunction8<R, E, A1, A2, A3, A4, A5, A6, A7, A8> f) { return f; }
    /**
     * 
     * function with 9 arguments, that declares to throw an exception
    
     * @param f function itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <R> return type
     * @return function itself
     */
    public static <R, E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8, A9> ExceptFunction9<R, E, A1, A2, A3, A4, A5, A6, A7, A8, A9> exfunction(ExceptFunction9<R, E, A1, A2, A3, A4, A5, A6, A7, A8, A9> f) { return f; }
    /**
     * 
     * function with 10 arguments, that declares to throw an exception
    
     * @param f function itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <R> return type
     * @return function itself
     */
    public static <R, E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> ExceptFunction10<R, E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> exfunction(ExceptFunction10<R, E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> f) { return f; }
    /**
     * 
     * function with 11 arguments, that declares to throw an exception
    
     * @param f function itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <R> return type
     * @return function itself
     */
    public static <R, E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> ExceptFunction11<R, E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> exfunction(ExceptFunction11<R, E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> f) { return f; }
    /**
     * 
     * function with 12 arguments, that declares to throw an exception
    
     * @param f function itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <R> return type
     * @return function itself
     */
    public static <R, E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> ExceptFunction12<R, E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> exfunction(ExceptFunction12<R, E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> f) { return f; }
    /**
     * 
     * function with 13 arguments, that declares to throw an exception
    
     * @param f function itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <R> return type
     * @return function itself
     */
    public static <R, E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> ExceptFunction13<R, E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> exfunction(ExceptFunction13<R, E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> f) { return f; }
    /**
     * 
     * function with 14 arguments, that declares to throw an exception
    
     * @param f function itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @param <R> return type
     * @return function itself
     */
    public static <R, E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> ExceptFunction14<R, E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> exfunction(ExceptFunction14<R, E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> f) { return f; }
    /**
     * 
     * function with 15 arguments, that declares to throw an exception
    
     * @param f function itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @param <A15> argument type 15
     * @param <R> return type
     * @return function itself
     */
    public static <R, E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> ExceptFunction15<R, E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> exfunction(ExceptFunction15<R, E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> f) { return f; }
    /**
     * 
     * function with 16 arguments, that declares to throw an exception
    
     * @param f function itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @param <A15> argument type 15
     * @param <A16> argument type 16
     * @param <R> return type
     * @return function itself
     */
    public static <R, E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16> ExceptFunction16<R, E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16> exfunction(ExceptFunction16<R, E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16> f) { return f; }
    /**
     * 
     * function with 17 arguments, that declares to throw an exception
    
     * @param f function itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @param <A15> argument type 15
     * @param <A16> argument type 16
     * @param <A17> argument type 17
     * @param <R> return type
     * @return function itself
     */
    public static <R, E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17> ExceptFunction17<R, E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17> exfunction(ExceptFunction17<R, E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17> f) { return f; }
    /**
     * 
     * function with 18 arguments, that declares to throw an exception
    
     * @param f function itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @param <A15> argument type 15
     * @param <A16> argument type 16
     * @param <A17> argument type 17
     * @param <A18> argument type 18
     * @param <R> return type
     * @return function itself
     */
    public static <R, E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18> ExceptFunction18<R, E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18> exfunction(ExceptFunction18<R, E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18> f) { return f; }
    /**
     * 
     * function with 19 arguments, that declares to throw an exception
    
     * @param f function itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @param <A15> argument type 15
     * @param <A16> argument type 16
     * @param <A17> argument type 17
     * @param <A18> argument type 18
     * @param <A19> argument type 19
     * @param <R> return type
     * @return function itself
     */
    public static <R, E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19> ExceptFunction19<R, E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19> exfunction(ExceptFunction19<R, E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19> f) { return f; }
    /**
     * 
     * method (void-returning function) with 0 arguments
    
     * @param f method itself (e.g. as a lambda)
     * @return method
     */
    public static  Method0 method(Method0 f) { return f; }
    /**
     * 
     * method (void-returning function) with 1 arguments
    
     * @param f method itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @return method
     */
    public static <A1> Method1<A1> method(Method1<A1> f) { return f; }
    /**
     * 
     * method (void-returning function) with 2 arguments
    
     * @param f method itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @return method
     */
    public static <A1, A2> Method2<A1, A2> method(Method2<A1, A2> f) { return f; }
    /**
     * 
     * method (void-returning function) with 3 arguments
    
     * @param f method itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @return method
     */
    public static <A1, A2, A3> Method3<A1, A2, A3> method(Method3<A1, A2, A3> f) { return f; }
    /**
     * 
     * method (void-returning function) with 4 arguments
    
     * @param f method itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @return method
     */
    public static <A1, A2, A3, A4> Method4<A1, A2, A3, A4> method(Method4<A1, A2, A3, A4> f) { return f; }
    /**
     * 
     * method (void-returning function) with 5 arguments
    
     * @param f method itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @return method
     */
    public static <A1, A2, A3, A4, A5> Method5<A1, A2, A3, A4, A5> method(Method5<A1, A2, A3, A4, A5> f) { return f; }
    /**
     * 
     * method (void-returning function) with 6 arguments
    
     * @param f method itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @return method
     */
    public static <A1, A2, A3, A4, A5, A6> Method6<A1, A2, A3, A4, A5, A6> method(Method6<A1, A2, A3, A4, A5, A6> f) { return f; }
    /**
     * 
     * method (void-returning function) with 7 arguments
    
     * @param f method itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @return method
     */
    public static <A1, A2, A3, A4, A5, A6, A7> Method7<A1, A2, A3, A4, A5, A6, A7> method(Method7<A1, A2, A3, A4, A5, A6, A7> f) { return f; }
    /**
     * 
     * method (void-returning function) with 8 arguments
    
     * @param f method itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @return method
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8> Method8<A1, A2, A3, A4, A5, A6, A7, A8> method(Method8<A1, A2, A3, A4, A5, A6, A7, A8> f) { return f; }
    /**
     * 
     * method (void-returning function) with 9 arguments
    
     * @param f method itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @return method
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9> Method9<A1, A2, A3, A4, A5, A6, A7, A8, A9> method(Method9<A1, A2, A3, A4, A5, A6, A7, A8, A9> f) { return f; }
    /**
     * 
     * method (void-returning function) with 10 arguments
    
     * @param f method itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @return method
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> Method10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> method(Method10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> f) { return f; }
    /**
     * 
     * method (void-returning function) with 11 arguments
    
     * @param f method itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @return method
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> Method11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> method(Method11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> f) { return f; }
    /**
     * 
     * method (void-returning function) with 12 arguments
    
     * @param f method itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @return method
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> Method12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> method(Method12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> f) { return f; }
    /**
     * 
     * method (void-returning function) with 13 arguments
    
     * @param f method itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @return method
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> Method13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> method(Method13<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> f) { return f; }
    /**
     * 
     * method (void-returning function) with 14 arguments
    
     * @param f method itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @return method
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> Method14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> method(Method14<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> f) { return f; }
    /**
     * 
     * method (void-returning function) with 15 arguments
    
     * @param f method itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @param <A15> argument type 15
     * @return method
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> Method15<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> method(Method15<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> f) { return f; }
    /**
     * 
     * method (void-returning function) with 16 arguments
    
     * @param f method itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @param <A15> argument type 15
     * @param <A16> argument type 16
     * @return method
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16> Method16<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16> method(Method16<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16> f) { return f; }
    /**
     * 
     * method (void-returning function) with 17 arguments
    
     * @param f method itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @param <A15> argument type 15
     * @param <A16> argument type 16
     * @param <A17> argument type 17
     * @return method
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17> Method17<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17> method(Method17<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17> f) { return f; }
    /**
     * 
     * method (void-returning function) with 18 arguments
    
     * @param f method itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @param <A15> argument type 15
     * @param <A16> argument type 16
     * @param <A17> argument type 17
     * @param <A18> argument type 18
     * @return method
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18> Method18<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18> method(Method18<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18> f) { return f; }
    /**
     * 
     * method (void-returning function) with 19 arguments
    
     * @param f method itself (e.g. as a lambda)
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @param <A15> argument type 15
     * @param <A16> argument type 16
     * @param <A17> argument type 17
     * @param <A18> argument type 18
     * @param <A19> argument type 19
     * @return method
     */
    public static <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19> Method19<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19> method(Method19<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19> f) { return f; }
    /**
     * 
     * method (void-returning function) with 0 arguments, that declares to throw an exception
    
     * @param f method itself (e.g. as a lambda)
     * @param <E> exception type
     * @return method
     */
    public static <E extends Exception> ExceptMethod0<E> exmethod(ExceptMethod0<E> f) { return f; }
    /**
     * 
     * method (void-returning function) with 1 arguments, that declares to throw an exception
    
     * @param f method itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @return method
     */
    public static <E extends Exception, A1> ExceptMethod1<E, A1> exmethod(ExceptMethod1<E, A1> f) { return f; }
    /**
     * 
     * method (void-returning function) with 2 arguments, that declares to throw an exception
    
     * @param f method itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @return method
     */
    public static <E extends Exception, A1, A2> ExceptMethod2<E, A1, A2> exmethod(ExceptMethod2<E, A1, A2> f) { return f; }
    /**
     * 
     * method (void-returning function) with 3 arguments, that declares to throw an exception
    
     * @param f method itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @return method
     */
    public static <E extends Exception, A1, A2, A3> ExceptMethod3<E, A1, A2, A3> exmethod(ExceptMethod3<E, A1, A2, A3> f) { return f; }
    /**
     * 
     * method (void-returning function) with 4 arguments, that declares to throw an exception
    
     * @param f method itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @return method
     */
    public static <E extends Exception, A1, A2, A3, A4> ExceptMethod4<E, A1, A2, A3, A4> exmethod(ExceptMethod4<E, A1, A2, A3, A4> f) { return f; }
    /**
     * 
     * method (void-returning function) with 5 arguments, that declares to throw an exception
    
     * @param f method itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @return method
     */
    public static <E extends Exception, A1, A2, A3, A4, A5> ExceptMethod5<E, A1, A2, A3, A4, A5> exmethod(ExceptMethod5<E, A1, A2, A3, A4, A5> f) { return f; }
    /**
     * 
     * method (void-returning function) with 6 arguments, that declares to throw an exception
    
     * @param f method itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @return method
     */
    public static <E extends Exception, A1, A2, A3, A4, A5, A6> ExceptMethod6<E, A1, A2, A3, A4, A5, A6> exmethod(ExceptMethod6<E, A1, A2, A3, A4, A5, A6> f) { return f; }
    /**
     * 
     * method (void-returning function) with 7 arguments, that declares to throw an exception
    
     * @param f method itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @return method
     */
    public static <E extends Exception, A1, A2, A3, A4, A5, A6, A7> ExceptMethod7<E, A1, A2, A3, A4, A5, A6, A7> exmethod(ExceptMethod7<E, A1, A2, A3, A4, A5, A6, A7> f) { return f; }
    /**
     * 
     * method (void-returning function) with 8 arguments, that declares to throw an exception
    
     * @param f method itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @return method
     */
    public static <E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8> ExceptMethod8<E, A1, A2, A3, A4, A5, A6, A7, A8> exmethod(ExceptMethod8<E, A1, A2, A3, A4, A5, A6, A7, A8> f) { return f; }
    /**
     * 
     * method (void-returning function) with 9 arguments, that declares to throw an exception
    
     * @param f method itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @return method
     */
    public static <E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8, A9> ExceptMethod9<E, A1, A2, A3, A4, A5, A6, A7, A8, A9> exmethod(ExceptMethod9<E, A1, A2, A3, A4, A5, A6, A7, A8, A9> f) { return f; }
    /**
     * 
     * method (void-returning function) with 10 arguments, that declares to throw an exception
    
     * @param f method itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @return method
     */
    public static <E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> ExceptMethod10<E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> exmethod(ExceptMethod10<E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> f) { return f; }
    /**
     * 
     * method (void-returning function) with 11 arguments, that declares to throw an exception
    
     * @param f method itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @return method
     */
    public static <E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> ExceptMethod11<E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> exmethod(ExceptMethod11<E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11> f) { return f; }
    /**
     * 
     * method (void-returning function) with 12 arguments, that declares to throw an exception
    
     * @param f method itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @return method
     */
    public static <E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> ExceptMethod12<E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> exmethod(ExceptMethod12<E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12> f) { return f; }
    /**
     * 
     * method (void-returning function) with 13 arguments, that declares to throw an exception
    
     * @param f method itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @return method
     */
    public static <E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> ExceptMethod13<E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> exmethod(ExceptMethod13<E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13> f) { return f; }
    /**
     * 
     * method (void-returning function) with 14 arguments, that declares to throw an exception
    
     * @param f method itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @return method
     */
    public static <E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> ExceptMethod14<E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> exmethod(ExceptMethod14<E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14> f) { return f; }
    /**
     * 
     * method (void-returning function) with 15 arguments, that declares to throw an exception
    
     * @param f method itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @param <A15> argument type 15
     * @return method
     */
    public static <E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> ExceptMethod15<E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> exmethod(ExceptMethod15<E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15> f) { return f; }
    /**
     * 
     * method (void-returning function) with 16 arguments, that declares to throw an exception
    
     * @param f method itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @param <A15> argument type 15
     * @param <A16> argument type 16
     * @return method
     */
    public static <E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16> ExceptMethod16<E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16> exmethod(ExceptMethod16<E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16> f) { return f; }
    /**
     * 
     * method (void-returning function) with 17 arguments, that declares to throw an exception
    
     * @param f method itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @param <A15> argument type 15
     * @param <A16> argument type 16
     * @param <A17> argument type 17
     * @return method
     */
    public static <E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17> ExceptMethod17<E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17> exmethod(ExceptMethod17<E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17> f) { return f; }
    /**
     * 
     * method (void-returning function) with 18 arguments, that declares to throw an exception
    
     * @param f method itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @param <A15> argument type 15
     * @param <A16> argument type 16
     * @param <A17> argument type 17
     * @param <A18> argument type 18
     * @return method
     */
    public static <E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18> ExceptMethod18<E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18> exmethod(ExceptMethod18<E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18> f) { return f; }
    /**
     * 
     * method (void-returning function) with 19 arguments, that declares to throw an exception
    
     * @param f method itself (e.g. as a lambda)
     * @param <E> exception type
     * @param <A1> argument type 1
     * @param <A2> argument type 2
     * @param <A3> argument type 3
     * @param <A4> argument type 4
     * @param <A5> argument type 5
     * @param <A6> argument type 6
     * @param <A7> argument type 7
     * @param <A8> argument type 8
     * @param <A9> argument type 9
     * @param <A10> argument type 10
     * @param <A11> argument type 11
     * @param <A12> argument type 12
     * @param <A13> argument type 13
     * @param <A14> argument type 14
     * @param <A15> argument type 15
     * @param <A16> argument type 16
     * @param <A17> argument type 17
     * @param <A18> argument type 18
     * @param <A19> argument type 19
     * @return method
     */
    public static <E extends Exception, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19> ExceptMethod19<E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19> exmethod(ExceptMethod19<E, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19> f) { return f; }

/*PPJAVA:TAIL*/
}
