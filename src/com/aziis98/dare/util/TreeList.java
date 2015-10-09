package com.aziis98.dare.util;

import java.io.*;
import java.util.*;

public class TreeList implements Serializable {

    private static final long serialVersionUID = 1L;

    private HashMap<Object, Object> list = new HashMap<>();

    public <K, V> void add(K key, V value) {
        list.put(key, value);
    }

    public <K> TreeList addCompound(K key) {
        TreeList treeList = new TreeList();
        add(key, treeList);
        return treeList;
    }

    public <V> void append(V value) {
        add(getNextIndex(), value);
    }

    public <V> TreeList appendCompound() {
        return addCompound(getNextIndex());
    }

    public <K> boolean hasKey(K key) {
        return list.containsKey(key);
    }

    @SuppressWarnings("unchecked")
    public <K, V> V get(K key) {
        return (V) list.get(key);
    }

    @SuppressWarnings("UnusedParameters")
    public <K, V> V get(K key, Class<V> valueType) {
        return this.get(key);
    }

    public <K> TreeList getCompound(K key) {
        return get(key, TreeList.class);
    }

    public int getNextIndex() {
        return list.size();
    }

}
