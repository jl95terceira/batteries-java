package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

/**
 * A simple converter from a list to a JSON array. 
 */
public class ListToJson {

    public static <T> Function1<javax.json.JsonValue, Iterable<T>> get(Function1<javax.json.JsonValue, T> value_converter) { return a -> {
        
        javax.json.JsonArrayBuilder ja = javax.json.Json.createArrayBuilder();
        for (T x: a) {
            
            ja.add(value_converter.call(x));
        }
        return ja.build();
    };}
}
