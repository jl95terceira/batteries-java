package jl95.util;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Set;

import static jl95.lang.SuperPowers.strict;
import static jl95.lang.SuperPowers.sleep;
import static jl95.lang.SuperPowers.Set;

import jl95.lang.variadic.Method1;

public class LifeMap<K> {
    
    private final StrictMap<K, Duration> map = strict(new ConcurrentHashMap<>());
    private Duration         step            = Duration.ofSeconds(1);
    private Method1<K>       lifeOverHandler = (x) -> {};
    private ThreadedRunnable task            = new ThreadedRunnable(() -> {
        Set<K> toRemove = Set();
        for (var e: map.entrySet()) {
            var key  = e.getKey();
            var life = e.getValue();
            var lifeRemaining = life.minus(step);
            if (lifeRemaining.isZero() || lifeRemaining.isNegative()) {
                toRemove.add(key);
            }
        }
        for (var key: toRemove) {
            map.remove(key);
        }
        for (var key: toRemove) {
            lifeOverHandler.accept(key);
        }
        sleep(step.toMillis());
    });
    
    public void setStep           (Duration d) {
        step = d;
    }
    public void setLifeOverHandler(Method1<K> h) {
        
    }
    public void put    (K key, Duration lifeSpan) {
        map.put(key, lifeSpan);
    }
    public void remove(K key) {
        map.remove(key);
    }
    public VoidAwaitable start  () {
        return task.start();
    }
    public VoidAwaitable stop   () {
        return task.stop();
    }
}
