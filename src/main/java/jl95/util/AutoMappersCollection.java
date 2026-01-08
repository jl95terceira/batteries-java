package jl95.util;

import jl95.lang.variadic.Method2;
import jl95.util.StrictMap;
import java.util.UUID;

public class AutoMappersCollection {

    public static <V> AutoMapper<Long, V> getSequentialAutoMapper(Method2  <Long, V> putMethod) {
        return new AutoMapper<>(putMethod) {
            Long i = 0L;
            @Override
            protected Long makeKey() {
                var cur = i;
                i = i + 1;
                return cur;
            }
        };
    }
    public static <V> AutoMapper<Long, V> getSequentialAutoMapper(StrictMap<Long, V> map) {
        return getSequentialAutoMapper(map::put);
    }
    public static <V> AutoMapper<UUID, V> getUuidAutoMapper      (Method2  <UUID, V> putMethod) {
        return new AutoMapper<>(putMethod) {
            @Override
            protected UUID makeKey() {
                return UUID.randomUUID();
            }
        };
    }
    public static <V> AutoMapper<UUID, V> getUuidAutoMapper      (StrictMap<UUID, V> map) {
        return getUuidAutoMapper(map::put);
    }
}
