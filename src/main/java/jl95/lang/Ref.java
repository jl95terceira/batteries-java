package jl95.lang;

public class Ref<T> extends DataClass {

    public T value;

    public Ref(T value) {this.value = value;}
    public Ref() {this(null);}

    @Override protected Iterable<?> data() {
        return null;
    }
}
