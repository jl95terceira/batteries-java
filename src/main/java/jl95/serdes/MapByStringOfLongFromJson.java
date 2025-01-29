package jl95.serdes;

import jl95.lang.variadic.*;

public class MapByStringOfLongFromJson {
    
    public static Function1<java.util.Map<String, Long>, javax.json.JsonValue> get() { return MapByStringFromJson.get(LongFromJson.get()); }
}
