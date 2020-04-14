package com.sbt.jschool.lesson10;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class Person {
    String name;
    int age;

    public Person(int age) {
        this.age = age;
        this.name = "null" + age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class Streams<T> {
    private List list;
    private Stream stream;
    private static Streams streams;

    public List<T> getList() {
        return list;
    }

    public void setCollection(List collection) {
        this.list = collection;
    }

    public static Streams of(List list) {
        streams = new Streams();
        streams.setCollection(list);
        return streams;
    }

    public Streams<T> filter(Predicate<? super T> predicate) {
        stream = streams.list.stream()
                .filter(predicate);
        return this;
    }

    public <R> Streams<T> transform(Function<? super T, ? super R> function) {
        stream = streams.list.stream()
                .map(function);
        return this;
    }

    public <K, U> Map toMap(Function<? super T, ? extends K> keyMapper,
                            Function<? super T, ? extends U> valueMapper) {
        return (Map) stream
                .collect(Collectors.toMap(keyMapper, valueMapper));
    }

    public static void main(String[] args) {
        List<Person> list = Arrays.asList(new Person(1),
                new Person(2),
                new Person(3),
                new Person(4),
                new Person(5));

        Streams<Person> integerStreams1 = Streams.of(list);
        Map objectObjectMap = integerStreams1
                .filter(p -> p.getAge() > 2)
                .transform(p -> new Person(p.getAge() + 30))
                .toMap(Person::getName, person -> person);
    }
}
