package jl95.serdes;

import java.net.InetSocketAddress;

import javax.json.JsonValue;

import jl95.lang.variadic.Function1;

/**
 * The counterpart to {@link InetSocketAddressToJson}.
 */
public class InetSocketAddressFromJson {

    public static Function1<InetSocketAddress, JsonValue> get() {
        return json -> {
            var jsono = MapByStringOfJsonFromJson.get().apply(json);
            return InetSocketAddress.createUnresolved(
                StringFromJson.get ().apply(jsono.get(InetSocketAddressToJson.Id.HOST.value)),
                IntegerFromJson.get().apply(jsono.get(InetSocketAddressToJson.Id.PORT.value))
            );
        };
    }
}
