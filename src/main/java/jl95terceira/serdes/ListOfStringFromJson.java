package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

public class ListOfStringFromJson {
    
    public static Function1<java.util.List<String>, javax.json.JsonValue> get() { return ListFromJson.get(j -> ((javax.json.JsonString) j).getString()); }
}
