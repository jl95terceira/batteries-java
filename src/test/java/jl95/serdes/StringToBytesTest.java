package jl95.serdes;

public class StringToBytesTest {
    
    public StringToBytesTest() {
    }
    
    @org.junit.Test public void test() {
    
        String s = "foobar";
        org.junit.Assert.assertEquals(s, StringUTF8FromBytes.get().call
                                        (StringUTF8ToBytes  .get().call(s)));
    }
}
