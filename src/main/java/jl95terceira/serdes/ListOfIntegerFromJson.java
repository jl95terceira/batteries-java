package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

public class ListOfIntegerFromJson {
    
    public static Function1<java.util.List<Integer>, javax.json.JsonValue> get() { return ListFromJson.get(j -> ((javax.json.JsonNumber) j).intValueExact()); }
}
