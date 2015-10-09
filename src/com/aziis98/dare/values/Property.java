package com.aziis98.dare.values;

import java.util.*;

public class Property<T> {

    private T value;

    private LinkedList<PropertyListner<T>> changeListners = new LinkedList<>();
    private LinkedList<PropertyListner<T>> setterListener = new LinkedList<>();

    public Property(T value) {
        this.value = value;
    }

    public Property<T> addChangeListner(PropertyListner<T> changeListner) {
        this.changeListners.add(changeListner);
        return this;
    }

    public Property<T> addValueListener(PropertyListner<T> setterListener) {
        this.setterListener.add(setterListener);
        return this;
    }

    public T get() {
        return value;
    }

    public void set(T value) {
        if (!this.value.equals(value))
        {
            for (PropertyListner<T> listner : changeListners)
            {
                listner.perform(value);
            }
        }

        this.value = value;

        for (PropertyListner<T> listner : this.setterListener)
        {
            listner.perform(value);
        }
    }

}
