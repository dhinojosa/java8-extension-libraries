package com.xyzcorp.models;

import java.util.Objects;
import java.util.StringJoiner;

public class MyTuple2<T> {
    private T first;
    private T second;

    public MyTuple2(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyTuple2<?> myTuple2 = (MyTuple2<?>) o;
        return Objects.equals(first, myTuple2.first) &&
            Objects.equals(second, myTuple2.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MyTuple2.class.getSimpleName() + "[", "]")
            .add("first=" + first)
            .add("second=" + second)
            .toString();
    }
}
