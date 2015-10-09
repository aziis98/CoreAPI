package com.aziis98.dare.util;

import com.aziis98.dare.interfaces.*;
import com.sun.istack.internal.*;

import java.util.*;
import java.util.function.*;

public class EList<T> implements Collection<T> {

    private LinkedList<T> list;

    public EList() {
        list = new LinkedList<>();
    }

    public EList(EList<T> self) {
        this.list = new LinkedList<>(self.list);
    }

    public T getFirst() {
        return list.getFirst();
    }

    public T getLast() {
        return list.getLast();
    }

    public T removeFirst() {
        return list.removeFirst();
    }

    public T removeLast() {
        return list.removeLast();
    }

    public void addFirst(T t) {
        list.addFirst(t);
    }

    public void addLast(T t) {
        list.addLast(t);
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public boolean add(T t) {
        return list.add(t);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }


    public boolean addAll(T[] array) {
        return addAll(Arrays.asList(array));
    }

    public boolean addAll(Collection<? extends T> c) {
        return list.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        return list.addAll(index, c);
    }

    public void clear() {
        list.clear();
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public T get(int index) {
        return list.get(index);
    }

    public T set(int index, T element) {
        return list.set(index, element);
    }

    public void add(int index, T element) {
        list.add(index, element);
    }

    public T remove(int index) {
        return list.remove(index);
    }

    public int indexOf(T o) {
        return list.indexOf(o);
    }

    public int lastIndexOf(T o) {
        return list.lastIndexOf(o);
    }

    public T peek() {
        return list.peek();
    }

    public T poll() {
        return list.poll();
    }

    public T remove() {
        return list.remove();
    }

    public T peekFirst() {
        return list.peekFirst();
    }

    public T peekLast() {
        return list.peekLast();
    }

    public T pollFirst() {
        return list.pollFirst();
    }

    public T pollLast() {
        return list.pollLast();
    }

    public void push(T t) {
        list.push(t);
    }

    public T pop() {
        return list.pop();
    }

    public boolean removeFirstOccurrence(Object o) {
        return list.removeFirstOccurrence(o);
    }

    public boolean removeLastOccurrence(Object o) {
        return list.removeLastOccurrence(o);
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public Iterator<T> iterator() {
        return list.iterator();
    }

    public Iterator<T> descendingIterator() {
        return list.descendingIterator();
    }

    public EList<T> cloneList() {
        return new EList<>(this);
    }

    @Override
    @SuppressWarnings({"unchecked", "NullableProblems"})
    public T[] toArray() {
        return (T[]) list.toArray();
    }

    @Override
    @SuppressWarnings({"NullableProblems", "unchecked"})
    public <T1> T1[] toArray(T1[] a) {
        return (T1[]) toArray();
    }


    ////////////////////////////////////////////////////////////////
    //////////////////     Custom Pipeline API     /////////////////
    ////////////////////////////////////////////////////////////////

    @SuppressWarnings("unchecked")

    /**
     * <ul>
     *     <li>A <code>null</code> value means that the element have to be removed</li>
     *     <li>An <code>empty array</code> means that the element have to be removed</li>
     *     <li>An <code>array of elements</code> means that the element have to be splitted</li>
     * </ul>
     */
    public EList<T> split(Splitter<T> splitter) {
        EList<T> newlist = new EList<>();
        for (T element : list)
        {
            T[] results = splitter.split(element);
            if (results != null && results.length > 0)
            {
                newlist.addAll(results);
            }
        }
        return newlist;
    }

    public <R> EList<R> filterType(Class<R> type) {
        return filter(type::isInstance).map(element -> (R) element);
    }

    public EList<T> filter(BiPredicate<T, Integer> predicate) {
        EList<T> newlist = new EList<>();
        int      index   = 0;
        for (T element : list)
        {
            if (predicate.test(element, index)) newlist.add(element);
            index++;
        }
        return newlist;
    }

    public EList<T> filter(Predicate<T> predicate) {
        EList<T> newlist = new EList<>();
        for (T element : list)
        {
            if (predicate.test(element)) newlist.add(element);
        }
        return newlist;
    }

    public <R> EList<R> map(Function<T, R> mapper) {
        EList<R> newlist = new EList<>();
        for (T element : list)
        {
            newlist.add(mapper.apply(element));
        }
        return newlist;
    }

    public T reduce(BinaryOperator<T> operator, @NotNull T identity) {
        T result = identity;
        for (T element : list)
        {
            result = operator.apply(result, element);
        }
        return result;
    }

    public T reduce(BinaryOperator<T> operator) {
        T result = null;
        for (T element : list)
        {
            if (result == null)
            {
                result = element;
            }
            else
            {
                result = operator.apply(result, element);
            }
        }
        return result;
    }

    public boolean ifAny(Predicate<T> tester) {
        for (T element : list)
        {
            if (tester.test(element)) return true;
        }
        return false;
    }

    public boolean ifAll(Predicate<T> tester) {
        for (T element : list)
        {
            if (!tester.test(element)) return false;
        }
        return true;
    }

    public EList<T> sort() {
        return sort(null);
    }

    public EList<T> sort(Comparator<T> comparator) {
        EList<T> newlist = new EList<>(this);
        newlist.list.sort(comparator);
        return newlist;
    }

}


































