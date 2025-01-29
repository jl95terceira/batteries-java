package jl95.serdes;

import jl95.lang.variadic.*;

public class MapByStringOfIntegerToJson {

    public static Function1<javax.json.JsonValue, java.util.Map<String, Integer>> get() { return MapByStringToJson.get(javax.json.Json::createValue); }
}
