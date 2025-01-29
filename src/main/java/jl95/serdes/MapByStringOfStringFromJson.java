package jl95.serdes;

import jl95.lang.variadic.*;

public class MapByStringOfStringFromJson {
    
    public static Function1<java.util.Map<String, String>, javax.json.JsonValue> get() { return MapByStringFromJson.get(StringFromJson.get()); }
}
