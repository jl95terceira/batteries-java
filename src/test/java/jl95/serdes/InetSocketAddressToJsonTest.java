package jl95.serdes;

import static jl95.lang.SuperPowers.I;
import static jl95.lang.SuperPowers.function;
import static jl95.lang.SuperPowers.tuple;

import java.net.InetSocketAddress;

public class InetSocketAddressToJsonTest {

    @org.junit.Test
    public void test() {
        for (var t: I(
            tuple("127.0.0.1", 1),
            tuple("123.123.123.123", 4242),
            tuple("foo.bar.com", 19999)
        )) {
            var addr = function(() -> InetSocketAddress.createUnresolved(t.a1, t.a2));
            org.junit.Assert.assertEquals(addr.apply(), InetSocketAddressFromJson.get().apply
                                                       (InetSocketAddressToJson  .get().apply
                                                       (addr.apply())));
            var addr2 = function(() -> InetSocketAddress.createUnresolved(t.a1, t.a2+1));
            org.junit.Assert.assertNotEquals(addr.apply(), InetSocketAddressFromJson.get().apply
                                                          (InetSocketAddressToJson  .get().apply
                                                          (addr2.apply())));
            var addr3 = function(() -> InetSocketAddress.createUnresolved("hello"+t.a1, t.a2));
            org.junit.Assert.assertNotEquals(addr.apply(), InetSocketAddressFromJson.get().apply
                                                          (InetSocketAddressToJson  .get().apply
                                                          (addr3.apply())));
            var addr4 = function(() -> InetSocketAddress.createUnresolved(t.a1+"world", t.a2));
            org.junit.Assert.assertNotEquals(addr.apply(), InetSocketAddressFromJson.get().apply
                                                          (InetSocketAddressToJson  .get().apply
                                                          (addr4.apply())));
        }
    }
}
