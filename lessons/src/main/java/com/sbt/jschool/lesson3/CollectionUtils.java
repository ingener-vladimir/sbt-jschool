package com.sbt.jschool.lesson3;

import java.util.*;

public class CollectionUtils {
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<? extends T> source, Object o) {
        return source.indexOf((T) o);
    }

//    public static<T> List limit(List source, int size) {
//    }

    public static <T> void add(List<? super T> source, Object o) {
        source.add((T) o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static <T> boolean containsAll(List<? extends T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    public static <T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
        Iterator<? extends T> iterator = c2.iterator();

        while (iterator.hasNext()) {
            if (c1.contains(iterator.next())) {
                return true;
            }
        }

        return false;
    }

//    public static<T> List<T> range(List<T> list, Object min, Object max) {
//    }
//
//    public static<T> List<? extends T> range(List<? extends T> list, Object min, Object max, Comparator comparator) {
//    }
    }
