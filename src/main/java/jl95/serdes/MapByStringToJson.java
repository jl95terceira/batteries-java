package jl95.serdes;

import jl95.lang.variadic.*;

public class MapByStringToJson {

    public static <V> Function1<javax.json.JsonValue, java.util.Map<String, V>> get(Function1<javax.json.JsonValue, V> value_fc) { return MapToJson.get(s -> s, value_fc); }
}
