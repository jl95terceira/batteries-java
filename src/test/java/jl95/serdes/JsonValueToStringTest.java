package jl95.serdes;

public class JsonValueToStringTest {
    
    public JsonValueToStringTest() {
    }
    
    @org.junit.Test        public void        test         () {
    
        org.junit.Assert.assertEquals(_Json.i, JsonFromString.get().call
                                              (JsonToString  .get().call(_Json.i)));
    }
}
