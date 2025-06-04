package jl95.util;

import java.util.UUID;

public class AutoHashcodedTest {

    public static class TestAutoHc extends AutoHashcoded {
        public TestAutoHc(Integer hc) {
            super(hc);
        }
    }

    @org.junit.Test
    public void test() {
        var a = new TestAutoHc(1234);
        org.junit.Assert.assertEquals(1234, a.hashCode());
    }
}
