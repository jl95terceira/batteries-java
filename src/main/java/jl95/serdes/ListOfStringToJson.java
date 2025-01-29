package jl95.serdes;

import jl95.lang.variadic.*;

public class ListOfStringToJson {

    public static Function1<javax.json.JsonValue, Iterable<String>> get() { return ListToJson.get(javax.json.Json::createValue); }
}
