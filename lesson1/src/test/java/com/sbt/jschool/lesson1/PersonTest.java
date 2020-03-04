package com.sbt.jschool.lesson1;

/**
 * Класс тестирования класса человека
 *
 * @autor Саньков Владимир
 * @version 0.1
 */

import org.junit.Assert;
import org.junit.Test;

public class PersonTest {
    private Person person = new Person(true, "John");

    @Test
    public void marry() {
        Person person1 = new Person(false, "Mary");
        Assert.assertTrue(person.marry(person1));
        Assert.assertFalse(person.marry(person1));
        Assert.assertFalse(person.marry(new Person(true, "Tobi")));
        Assert.assertFalse(person1.marry(person));
        Assert.assertTrue(person.marry(new Person(false, "Ann")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testException(){
        person.marry(null);
    }
}