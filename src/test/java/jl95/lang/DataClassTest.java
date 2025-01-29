package jl95.lang;

import static jl95.lang.stt.*;

public class DataClassTest {

    public static class Foo extends DataClass {

        public String foo = "foo";
        public String bar = "bar";
        
        @Override protected Iterable<?> data() { return I(this.foo, this.bar); }
    }
    public static class Universe extends DataClass {
        
        public Integer answer = 42;

        @Override protected Iterable<?> data() { return I(this.answer); }
    }
    
    @org.junit.Test public void test_equals() {
        
        System.out.println(new Foo());
        System.out.println(new Universe());
        org.junit.Assert.assertEquals   (new Foo(), new Foo());
        org.junit.Assert.assertNotEquals(new Foo(), new Universe());
        org.junit.Assert.assertEquals   (new Universe(), new Universe());
        Foo foo = new Foo();
        foo.foo = foo.foo + "def";
        org.junit.Assert.assertNotEquals(foo,       new Foo());
        Universe bar = new Universe();
        bar.answer = bar.answer + 666;
        org.junit.Assert.assertNotEquals(bar,       new Universe());
        for (int i: I.range(1000)) {
            
            foo.foo  = java.util.UUID.randomUUID().toString();
            Foo foo2 = new Foo();
            foo2.foo = foo.foo;
            org.junit.Assert.assertEquals(foo2, foo);
            bar.answer  = java.util.UUID.randomUUID().toString().hashCode();
            Universe bar2 = new Universe();
            bar2.answer = bar.answer;
            org.junit.Assert.assertEquals(bar2, bar);
        }
    }
    @org.junit.Test public void test_hash  () {
        
        System.out.println(new Foo());
        System.out.println(new Universe());
        org.junit.Assert.assertEquals(new Foo     ().hashCode(), 
                                      new Foo     ().hashCode());
        org.junit.Assert.assertEquals(new Universe().hashCode(), 
                                      new Universe().hashCode());
        Foo foo = new Foo();
        foo.bar = null;
        try {
            
            foo.hashCode();
        } 
        catch(NullPointerException ex) {
            
            org.junit.Assert.fail();
        }
    }
}
