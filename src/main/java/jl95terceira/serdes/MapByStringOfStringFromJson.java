package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

public class MapByStringOfStringFromJson {
    
    public static Function1<java.util.Map<String, String>, javax.json.JsonValue> get() { return MapByStringFromJson.get(StringFromJson.get()); }
}
