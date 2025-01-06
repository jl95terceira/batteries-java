package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

public class MapByStringOfJsonFromJson {
    
    public static Function1<java.util.Map<String, javax.json.JsonValue>, javax.json.JsonValue> get() { return MapByStringFromJson.get(j -> j); }
}
