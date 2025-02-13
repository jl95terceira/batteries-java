package jl95.serdes;

import static jl95.lang.SuperPowers.*;

import java.net.InetSocketAddress;

import javax.json.Json;
import javax.json.JsonValue;

import jl95.lang.variadic.Function1;

/**
 * A simple converter from {@link java.net.InetSocketAddress} to Json.
 */
public class InetSocketAddressToJson {

    public enum Id {
        HOST("host"),
        PORT("port");
        public final String value;
        Id(String value){this.value = value;}
    }
    
    public static Function1<JsonValue, InetSocketAddress> get() { return addr -> MapByStringOfJsonToJson.get().apply(I(
        tuple(Id.HOST, StringToJson .get().apply(addr.getHostName())),
        tuple(Id.PORT, IntegerToJson.get().apply(addr.getPort()))
    ).toMap(t -> t.a1.value, t -> t.a2)); }
}
