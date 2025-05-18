package jl95.util;

import java.util.UUID;

public class AutoHashcoded {

    private final Integer hc;

    protected AutoHashcoded(Integer hc) {
        this.hc = hc;
    }
    public AutoHashcoded() {
        this(UUID.randomUUID().hashCode());
    }

    @Override
    public int hashCode() {
        return hc;
    }
}
