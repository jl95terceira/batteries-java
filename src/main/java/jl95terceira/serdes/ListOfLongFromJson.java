package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

public class ListOfLongFromJson {
    
    public static Function1<java.util.List<Long>, javax.json.JsonValue> get() { return ListFromJson.get(j -> ((javax.json.JsonNumber) j).longValueExact()); }
}
