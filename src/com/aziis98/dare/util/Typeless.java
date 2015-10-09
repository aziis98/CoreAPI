package com.aziis98.dare.util;

public class Typeless {

    private Object object;

    public <T> Typeless(T object) {
        this.object = object;
    }

    public <T> boolean instanceOf(Class<T> clazz) {
        return clazz.isInstance(object);
    }

    @SuppressWarnings("unchecked")
    public <T> T get() {
        return (T) object;
    }
}
