package jl95.lang;

import jl95.util.StrictMap;
import jl95.util.StrictSet;
import static jl95.lang.SuperPowers.*;
import jl95.lang.variadic.*;
import java.util.*;

/**
 * A super-powered iterable. 
 * <br/>
 * Use static methods {@link SuperPowers#I(java.lang.Object...)} and {@link #of(java.lang.Iterable)} to obtain
 * an iterable with various functional programming-oriented methods, such as {@link #map(jl95.lang.variadic.Function1)} and {@link #reduce(java.lang.Object, jl95.lang.variadic.Function2)}.
 * This interface also holds various other powerful static methods to perform common operations. 
 * 
 * @param <T> element type
 */
public interface I <T> extends Iterable<T> {

    /**
     * construct of a regular iterable
     * @param <T> type of elements
     * @param xx elements
     * @return super-powered iterable
     */
    public static <T> I<T>   of     (Iterable<T>       xx) { return () -> xx.iterator(); }
    public static <T> I<T>   ofArray(T[]               xx) { 
        
        return () -> new Iterator<T>() {
            
            Integer i = 0;
            
            @Override public boolean hasNext() { return i < xx.length; }
            @Override public T       next   () { i++; return xx[i-1]; }
        }; 
    }
    /**
     * range of integers from 0 to n-1
     * @param n n
     * @return range
     */
    public static I<Integer> range(Integer           n) {

        return () -> new Iterator<Integer>() {

            private Integer cur = 0;

            @Override public boolean hasNext() { return cur < n; }
            @Override public Integer next   () { cur += 1; return cur - 1; }
        };
    }
    /**
     * check if any true
     * @param xx iterable of booleans
     * @return any true
     */
    public static Boolean    any  (Iterable<Boolean> xx) {

        return I.of(xx).any(x -> x);
    }
    /**
     * check if all true
     * @param xx iterable of booleans
     * @return all true
     */
    public static Boolean    all  (Iterable<Boolean> xx) {

        return I.of(xx).all(x -> x);
    }
    /**
     * flatten the given array of iterables i.e. chain those iterables
     * @param <T> element type
     * @param xx iterables
     * @return flattened iterables
     */
    public static <T> I<T>   flat (Iterable<T>...    xx) {

        return I(xx).flatmap(x -> x);
    }
    /**
     * @param <K> key type
     * @param <V> value type
     * @param f grouping function of a value
     * @param vv values
     * @return grouping of values by the keys returned by the given function - map of sets
     */
    public static <K, V> Map<K, ? extends Set<V>>
                             group(Function1<K, V>    f,
                                   Set<V>   vv) {

        return I.of(vv).apply(new HashMap<>(), (v, map) -> {

            K k                      = f.call(v);
            Set<V> groupvv = map.containsKey(k)
                                     ? (map.get(k))
                                     : function(() -> {
              
                                         Set<V> l = new HashSet<>();
                                         map.put(k, l);
                                         return l;
              
                                     }).call();
            groupvv.add(v);

        });
    }
    /**
     * @param <K> key type
     * @param <V> value type
     * @param f grouping function of a value
     * @param vv values
     * @return grouping of values by the keys returned by the given function - map of lists
     */
    public static <K, V> Map<K, ? extends List<V>>
                             group(Function1<K, V>    f,
                                   Iterable<V>        vv) {

        return I.of(vv).apply(new HashMap<>(), (v, map) -> {

            K                 k       = f.call(v);
            List<V> groupvv = map.containsKey(k)
                                      ? (map.get(k))
                                      : function(() -> {
              
                                          List<V> l = new ArrayList<>();
                                          map.put(k, l);
                                          return l;
              
                                      }).call();
            groupvv.add(v);

        });
    }
    /**
     * @param <T> value type
     * @param xx values
     * @return zipped values, so that <code>[a1, a2, ..., an], [b1, b2, ..., bn], [c1, c2, ..., cn], ..., [z1, z2, ... zn]</code> becomes <code>[[a1, b1, ..., z1], [a2, b2, ..., z2], ..., [an, bn, ..., zn]]</code>. If an iterable is exhausted before the others, the zipping stops there. 
     */
    public static <T> I<List<T>>
                             zip  (Iterable<T>...     xx) {

        return () -> new Iterator<List<T>>() {

            private final I<Iterator<T>> itt        = I.of(I(xx).map(x  -> x.iterator()).toList());
            private final I<Boolean>     itthasNext = itt  .map(it -> it.hasNext());
            private final I<T>           ittnext    = itt  .map(it -> it.next   ());

            @Override public boolean           hasNext() {return I.all(itthasNext);}
            @Override public List<T> next   () {return ittnext.toList();}
        };
    }
    /**
     * @param <A> type A
     * @param <B> type B
     * @param aa values A
     * @param bb values B
     * @return zipped values, so that <code>[a1, a2, ..., an], [b1, b2, ..., bn]</code> becomes <code>[(a1, b1), (a2, b2), ..., (an, bn)]</code>. If an iterable is exhausted before the other, the zipping stops there. 
     */
    public static <A, B> I<Tuple2<A, B>> 
                             zip  (Iterable<A>        aa, 
                                   Iterable<B>        bb) {

        return () -> new Iterator<Tuple2<A,B>>() {

            private final Iterator<A> aait = aa.iterator();
            private final Iterator<B> bbit = bb.iterator();

            @Override public boolean      hasNext() {return aait.hasNext() && bbit.hasNext();}
            @Override public Tuple2<A, B> next   () {return tuple(aait.next(), bbit.next());}
        };
    }
    /**
     * repeat value a finite number of times
     * @param <T> value type
     * @param v value
     * @param n number of times
     * @return finite iterable
     */
    public static <T>    I<T> repeat(T                 v,
                                    Integer            n) {
        
        return () -> new Iterator<T>() {
         
            Integer i = 0;
            
            @Override public boolean hasNext() { return i < n; }
            @Override public T       next   () { i = i+1; return v; }
        };
    }
    /**
     * repeat value to infinity
     * @param <T> value type
     * @param v value
     * @return infinite iterable
     */
    public static <T>    I<T>
                              repeat(T                 v) {
        
        return () -> new Iterator<T>() {
            
            @Override public boolean hasNext() { return true; }
            @Override public T       next   () { return v; }
        };
    }
    public static <T>    I<T> cycle (I<T>              xx) {
        
        return () -> new Iterator<>() {

            List<T> xxList = I.of(xx).toList();
            Iterator<T> it = xxList.iterator();

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public T next() {
                var x = it.next();
                if (!it.hasNext()) {
                    it = xxList.iterator();
                }
                return x;
            }
        };
    }

    /**
     * map this iterable to a new one according to a mapping function
     * @param <U> new iterable value type
     * @param f mapping function
     * @return mapped iterable
     */
    public default <U>                         I<U>    map      (Function1<U, 
                                                                           T>       f) { 

        return () -> new Iterator<U>() {

            Iterator<T> it = I.this.iterator();

            @Override public boolean hasNext() { return it.hasNext(); }
            @Override public U       next   () { return f.call(it.next()); }
        };
    }
    /**
     * get enumerated values - tuples [index, value]
     * @param i0 value of 1st index
     * @return enumerated values
     */
    public default                             I<Tuple2<Integer, T>>
                                                       enumer   (Integer            i0) {

        return () -> new Iterator<Tuple2<Integer, T>>() {

            private final Iterator<T> it = I.this.iterator();
            private       Integer     i  = i0;

            @Override public boolean            hasNext() {return it.hasNext();}
            @Override public Tuple2<Integer, T> next   () {i += 1; return tuple(i - 1, it.next());}
        };
    }
    /**
     * get enumerated values - tuples [index, value], where the index starts at 0
     * @return enumerated values
     */
    public default                             I<Tuple2<Integer, T>>
                                                       enumer   () { return enumer(0); }
    /**
     * reduce this iterable to a value according to a reducing function
     * @param <U> reduced value type
     * @param i indentity / starting value
     * @param f reducing function
     * @return reduced iterable as value
     */
    public default <U>                         U       reduce   (U                  i,
                                                                 Function2<U, 
                                                                           U, 
                                                                           T>       f) {

        for (T x: this) i = f.call(i, x);
        return i;
    }
    /**
     * filter this iterable according to a filtering function
     * @param f filtering function
     * @return filtered iterable
     */
    public default                             I<T>    filter   (Function1<Boolean, 
                                                                           T> f) {

        return () -> new Iterator<T>() {

            Iterator<T> it = I.this.iterator();
            T banked = null;

            @Override public  boolean hasNext() {

                bank();
                return banked != null;
            }
            @Override public  T       next   () {

                bank();
                T next = banked;
                banked = null;
                return next;
            }
                      private void    bank   () {

                if (banked != null) return;
                while (it.hasNext()) {

                    T candidate = it.next();
                    if (!f.call(candidate)) continue;
                    banked = candidate;
                    break;
                }
            }
        };
    }
    /**
     * apply all elements of this iterable to a value according to an applying function and return said value
     * @param <U> applied value type
     * @param i identity / starting value
     * @param f applying function
     * @return applied iterable as value
     */
    public default <U>                         U       apply    (U                  i,
                                                                 Method2  <T, 
                                                                           U>       f) {

        for (T x: this) f.call(x, i);
        return i;
    }
    /**
     * peek this iterable for every element and return a new iterable with the same elements
     * @param f peeking function
     * @return unmodified iterable
     */
    public default                             I<T>    peek     (Method1  <T>       f) {

        return map(x -> {f.call(x); return x;});
    }
    /**
     * evaluate whether any element of this iterable complies with the predicate
     * @param f predicate
     * @return evaluation
     */
    public default                             Boolean any      (Function1<Boolean, 
                                                                           T>       f) {

        for (T x: this) if (f.call(x)) return true;
        return false;
    }
    /**
     * evaluate whether all elements of this iterable comply with a predicate
     * @param f predicate
     * @return evaluation
     */
    public default                             Boolean all      (Function1<Boolean,
                                                                           T>       f) {

        for (T x: this) if (!f.call(x)) return false;
        return true;
    }
    /**
     * map this iterable to a new one according to a flat-mapping function (1 input, multiple outputs - flattened in resulting iterable)
     * @param <U> new iterable value type
     * @param f flat-mapping function
     * @return flat-mapped iterable
     */
    public default <U>                         I<U>    flatmap  (Function1<Iterable<U>,
                                                                           T>       f) {

        return () -> new Iterator<U>() {

            Iterator<T> it  = I.this.iterator();
            Iterator<U> it_ = SuperPowers.<U>I().iterator();

            @Override public  boolean hasNext() {

                bank();
                return it_.hasNext();
            }
            @Override public  U       next   () {

                bank();
                return it_.next();
            }
                      private void    bank   () {

                if (it_.hasNext()) return;
                if (!it.hasNext()) return;
                while (it.hasNext() && !it_.hasNext()) {

                    it_ = f.call(it.next()).iterator();
                }
            }
        };
    }
    public default                             I<T>    cycle    () {
        return cycle(this);
    }
    /**
     * make a list from this iterable
     * @param <L> list type
     * @param i list to be populated
     * @return list
     */
    public default        <L extends List<T>>  L       to       (L                  i) {

        return apply(i, (x, list) -> list.add(x));
    }
    /**
     * make a set from this iterable
     * @param <S> set type
     * @param i set to be populated
     * @return set
     */
    public default        <S extends Set<T>>   S       to       (S                  i) {

        return apply(i, (x, set) -> set.add(x));
    }
    /**
     * make a STRICT set from this iterable
     * @param <S> set type
     * @param i set to be populated
     * @return set
     */
    public default        <S extends StrictSet<T>> S   to       (S                  i) {

        return apply(i, (x, set) -> set.add(x));
    }
    /**
     * make a map from this iterable
     * @param <K> key type
     * @param <V> value type
     * @param <M> map type
     * @param k key as function of each element
     * @param v value as function of each element
     * @param i map to be populated
     * @return map
     */
    public default <K, V, M extends Map<K, V>> M       to       (M                  i,
                                                                 Function1<K, T>    k,
                                                                 Function1<V, T>    v) {

        return apply(i, (x, map) -> map.put(k.call(x), v.call(x)));
    }
    /**
     * make a map from this iterable
     * @param <K> key type
     * @param <V> value type
     * @param <M> map type
     * @param k key as function of each element
     * @param v value as function of each element
     * @param i map to be populated
     * @return map
     */
    public default <K, V, M extends StrictMap<K, V>> M to       (M                  i,
                                                                 Function1<K, T>    k,
                                                                 Function1<V, T>    v) {

        return apply(i, (x, map) -> map.put(k.apply(x), v.apply(x)));
    }
    /**
     * populate an array from this iterable
     * @param g array supplier
     * @return array
     */
    public default                             T[]     to       (T[]                g) {

        int i = 0;
        for (T x: this) g[i++] = x;
        return g;
    }
    /**
     * return as (array) list
     * @return list
     */
    public default                   ArrayList<T>      toList   () {

        return to(new ArrayList<>());
    }
    /**
     * return as (array) list, mapped by function
     * @param <U> type of mapped elements
     * @param f mapping function
     * @return list
     */
    public default <U>               ArrayList<U>      toList   (Function1<U, T>    f) {

        return map   (f)
              .toList();
    }
    /**
     * return as (hash) set
     * @return set
     */
    public default                   HashSet  <T>      toSet    () {

        return to(new HashSet<>());
    }
    /**
     * return as (hash) map
     * @param <K> key type
     * @param <V> value type
     * @param k key
     * @param v value
     * @return map
     */
    public default <K, V>            HashMap  <K, V>   toMap    (Function1<K, T>    k,
                                                                 Function1<V, T>    v) {

        return to(new HashMap<>(), k, v);
    }
    /**
     * return as (hash) map, where the values are this iterable's elements
     * @param <K> key type
     * @param k key
     * @return map
     */
    public default <K>               HashMap  <K, T>   toMap    (Function1<K, T>    k) {

        return toMap(k, x -> x);
    }
    
    /* ********** */
    /* DEPRECATED */
    /* ********** */

    /**
     * return as (array) list
     * @return list
     * @deprecated Please use "toList()"
     */
    @Deprecated
    public default                   ArrayList<T>      List     () { return toList(); }
    /**
     * return as (array) list, mapped by function
     * @param <U> type of mapped elements
     * @param f mapping function
     * @return list
     * @deprecated Please use "toList(f)"
     */
    @Deprecated
    public default <U>               ArrayList<U>      List     (Function1<U, T>    f) { return toList(f); }
    /**
     * return as (hash) set
     * @return set
     * @deprecated Please use "toSet()"
     */
    @Deprecated
    public default                   HashSet  <T>      Set      () { return toSet(); }
    /**
     * return as (hash) map
     * @param <K> key type
     * @param <V> value type
     * @param k key
     * @param v value
     * @return map
     * @deprecated Please use "toMap(k, v)"
     */
    @Deprecated
    public default <K, V>            HashMap  <K, V>   Map      (Function1<K, T>    k,
                                                                 Function1<V, T>    v) { return toMap(k, v); }
    /**
     * return as (hash) map, where the values are this iterable's elements
     * @param <K> key type
     * @param k key
     * @return map
     * @deprecated Plase use "toMap(k)"
     */
    @Deprecated
    public default <K>               HashMap  <K, T>   Map      (Function1<K, T>    k) { return toMap(k); }
}
