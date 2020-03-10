package com.sbt.jschool.lesson1;

public class Main {
    public static void main(String[] args) {
        Person person = new Person(true, "John");
        Person person1 = new Person(false, "Mary");
        person.marry(person1);
        person.marry(person1);
        person.marry(new Person(true, "Anya"));

        String str = (String)(new Integer(0) + new Long(10));
    }
}
