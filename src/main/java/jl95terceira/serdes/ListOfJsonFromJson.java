package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

public class ListOfJsonFromJson {
    
    public static Function1<java.util.List<javax.json.JsonValue>, javax.json.JsonValue> get() { return ListFromJson.get(j -> j); }
}
