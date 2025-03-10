package jl95.lang;

import java.util.UUID;

public class AutoMappersCollection {

    public static <V> AutoMapper<Long, V> getSequentialAutoMapper(StrictMap<Long, V> map) {
        return new AutoMapper<>(map) {
            Long i = 0L;
            @Override
            protected Long makeKey() {
                var cur = i;
                i = i + 1;
                return cur;
            }
        };
    }
    public static <V> AutoMapper<UUID, V> getUuidAutoMapper      (StrictMap<UUID, V> map) {
        return new AutoMapper<>(map) {
            @Override
            protected UUID makeKey() {
                return UUID.randomUUID();
            }
        };
    }
}
