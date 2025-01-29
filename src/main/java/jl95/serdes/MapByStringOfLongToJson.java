package jl95.serdes;

import jl95.lang.variadic.*;

public class MapByStringOfLongToJson {

    public static Function1<javax.json.JsonValue, java.util.Map<String, Long>> get() { return MapByStringToJson.get(javax.json.Json::createValue); }
}
