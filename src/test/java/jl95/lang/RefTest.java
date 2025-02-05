package jl95.lang;

public class RefTest {

    @org.junit.Test
    public void test() {
        var r = new Ref<String>();
        org.junit.Assert.assertNull(r.value);
        r.value = "abc";
        org.junit.Assert.assertEquals("abc", r.value);
        var r2 = new Ref<>("abc");
        org.junit.Assert.assertEquals(r.value, r2.value);
        org.junit.Assert.assertEquals(r      , r2);
        var r3 = new Ref<>("def");
        org.junit.Assert.assertNotEquals(r.value, r3.value);
        org.junit.Assert.assertNotEquals(r      , r3);
    }
    @org.junit.Test
    public void testNull() {
        var r = new Ref<Void>();
        System.out.println(r); // check if toString() does not blow up
    }
}
