package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

/**
 * A simple converter from JSON to string. 
 */
public class JsonToString {
    
    public static Function1<String, javax.json.JsonValue> get() { return a -> a.toString(); }
}
