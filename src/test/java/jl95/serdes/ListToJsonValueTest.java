package jl95.serdes;

public class ListToJsonValueTest {
    
    @org.junit.Test public void testStrings() {
    
        java.util.List<String> list = new java.util.ArrayList<>();
        list.add("foobar");
        list.add("it's me");
        org.junit.Assert.assertEquals(list, ListOfStringFromJson.get().call
                                           (ListOfStringToJson  .get().call(list)));
    }
    @org.junit.Test public void testInts   () {
    
        java.util.List<Integer> list = new java.util.ArrayList<>();
        list.add(123);
        list.add(42);
        org.junit.Assert.assertEquals(list, ListOfIntegerFromJson.get().call
                                           (ListOfIntegerToJson  .get().call(list)));
    }
    @org.junit.Test public void testLongs  () {
    
        java.util.List<Long> list = new java.util.ArrayList<>();
        list.add(123L);
        list.add(42L);
        org.junit.Assert.assertEquals(list, ListOfLongFromJson.get().call
                                           (ListOfLongToJson  .get().call(list)));
    }
    
}
