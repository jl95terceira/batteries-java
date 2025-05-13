package jl95.util;

import java.util.concurrent.CompletableFuture;
import static jl95.lang.SuperPowers.sleep;
import jl95.lang.VoidAwaitable;
import jl95.lang.variadic.Method0;

public class ThreadedRunnable {

    private final Method0 runnable;
    private final Long    pause;
    private final Object  sync = new Object();
    private       VoidAwaitable startAwaitable;
    private       VoidAwaitable stopAwaitable;
    private       Boolean isRunning = false;
    private       Boolean toStop    = false;
    
    public ThreadedRunnable(Method0 runnable, Long pause) {
        this.runnable = runnable;
        this.pause    = pause;
        var stopCompletable = new CompletableFuture<Void>();
        this.stopAwaitable = VoidAwaitable.of(stopCompletable);
        stopCompletable.complete(null);
    }
    public ThreadedRunnable(Method0 runnable) {
        this(runnable, 0L);
    }
    
    synchronized public VoidAwaitable start    () {
        
        if (isRunning()) {
            return startAwaitable;
        }
        var startCompletable = new CompletableFuture<Void>();
        var stopCompletable  = new CompletableFuture<Void>();
        startAwaitable = VoidAwaitable.of(startCompletable);
        stopAwaitable  = VoidAwaitable.of(stopCompletable);
        toStop = false;
        new Thread(() -> {
            startCompletable.complete(null);
            while (!toStop) {
                runnable.accept();
                if (pause > 0) {
                    sleep(pause);
                }
            }
            isRunning = false;
            stopCompletable.complete(null);
        }).start();
        isRunning = true;
        return startAwaitable;
    }
    synchronized public Boolean       isRunning() {return this.isRunning;}
    synchronized public VoidAwaitable stop     () {
        
        if (!isRunning()) {
            return stopAwaitable;
        }
        toStop = true;
        return stopAwaitable;
    }
}
