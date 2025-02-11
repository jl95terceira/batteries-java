package jl95.lang;

public abstract class SdHooked {

    protected SdHooked() {

        Runtime.getRuntime().addShutdownHook(new Thread(this::sdHook));
    }

    protected abstract void sdHook();
}
