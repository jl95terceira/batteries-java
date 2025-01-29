package jl95.serdes;

import jl95.lang.variadic.*;

public class MapByStringOfIntegerFromJson {
    
    public static Function1<java.util.Map<String, Integer>, javax.json.JsonValue> get() { return MapByStringFromJson.get(IntegerFromJson.get()); }
}
