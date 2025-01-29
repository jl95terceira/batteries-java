package jl95.serdes;

import jl95.lang.variadic.*;

public class ListOfIntegerToJson {

    public static Function1<javax.json.JsonValue, Iterable<Integer>> get() { return ListToJson.get(javax.json.Json::createValue); }
}
