package jl95.serdes;

import jl95.lang.variadic.*;

/**
 * A simple converter from string to JSON. 
 */
public class StringToJson {
    
    public static Function1<javax.json.JsonValue, String> get() { return javax.json.Json::createValue; }
}
