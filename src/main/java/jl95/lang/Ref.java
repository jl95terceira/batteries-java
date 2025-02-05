package jl95.lang;

import static jl95.lang.SuperPowers.*;

public class Ref<T> extends DataClass {

    public T value;

    public Ref(T value) {this.value = value;}
    public Ref() {this(null);}

    @Override protected Iterable<?> data() {
        return I(value);
    }
}
