package jl95.util;

public abstract class SdHooked {

    protected SdHooked() {

        Runtime.getRuntime().addShutdownHook(new Thread(this::sdHook));
    }

    protected abstract void sdHook();
}
