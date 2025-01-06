package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

/**
 * A simple converter from string to JSON. 
 */
public class StringToJson {
    
    public static Function1<javax.json.JsonValue, String> get() { return javax.json.Json::createValue; }
}
