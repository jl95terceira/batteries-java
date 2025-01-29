package jl95.serdes;

import jl95.lang.variadic.*;

/**
 * A simple converter from JSON to string. 
 */
public class JsonFromString {
    
    public static Function1<javax.json.JsonValue, String> get() { return b -> javax.json.Json.createReader(new java.io.StringReader(b)).readValue(); }
    
    
}
