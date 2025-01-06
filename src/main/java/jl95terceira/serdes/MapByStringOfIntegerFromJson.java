package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

public class MapByStringOfIntegerFromJson {
    
    public static Function1<java.util.Map<String, Integer>, javax.json.JsonValue> get() { return MapByStringFromJson.get(IntegerFromJson.get()); }
}
