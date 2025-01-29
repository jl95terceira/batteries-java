package jl95.serdes;

import jl95.lang.variadic.*;

public class MapByStringFromJson {
    
    public static <V> Function1<java.util.Map<String, V>, javax.json.JsonValue> get(Function1<V, javax.json.JsonValue> value_fc) { return MapFromJson.get(s -> s, value_fc); }
}
