package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

public class ListOfJsonToJson {

    public static Function1<javax.json.JsonValue, Iterable<javax.json.JsonValue>> get() { return ListToJson.get(j -> j); }
}
