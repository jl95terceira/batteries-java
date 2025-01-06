package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

/**
 * A simple converter from JSON to string. 
 */
public class JsonFromString {
    
    public static Function1<javax.json.JsonValue, String> get() { return b -> javax.json.Json.createReader(new java.io.StringReader(b)).readValue(); }
    
    
}
