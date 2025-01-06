package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

public class MapByStringOfLongFromJson {
    
    public static Function1<java.util.Map<String, Long>, javax.json.JsonValue> get() { return MapByStringFromJson.get(LongFromJson.get()); }
}
