package jl95.serdes;

import jl95.lang.variadic.*;

public class MapByStringOfJsonFromJson {
    
    public static Function1<java.util.Map<String, javax.json.JsonValue>, javax.json.JsonValue> get() { return MapByStringFromJson.get(j -> j); }
}
