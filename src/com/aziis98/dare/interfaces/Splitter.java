package com.aziis98.dare.interfaces;

public interface Splitter<T> {

    T[] split(T element);

    @SuppressWarnings("unchecked")
    static <T> T[] of(T... elements) {
        return elements;
    }

}
