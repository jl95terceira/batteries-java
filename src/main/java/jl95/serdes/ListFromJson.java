package jl95.serdes;

import jl95.lang.variadic.*;

/**
 * A simple reverter from a JSON array to a list. 
 */
public class ListFromJson {

    public static <T> Function1<java.util.List<T>, javax.json.JsonValue> get(Function1<T, javax.json.JsonValue> value_reverter) { return b -> {
        
        javax.json.JsonArray b_ja = b.asJsonArray();
        java.util.List<T>    l    = new java.util.ArrayList<>(b_ja.size());
        for (javax.json.JsonValue e: b_ja) {
            
            l.add(value_reverter.call(e));
        }
        return l;
    }; }
}
