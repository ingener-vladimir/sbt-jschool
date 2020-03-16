package com.sbt.jschool.lesson3;

import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;

public class LinkedListTest {

    private LinkedList<Number> linkedList;

    @Before
    public void init(){
        linkedList = new LinkedList<>();
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);
    }

    @Test
    public void add() {
        assertTrue(linkedList.add(15));
        assertTrue(linkedList.add(1, 20));
    }

    @Test
    public void remove() {
        assertEquals((int)20, (int)linkedList.remove(1));
    }

    @Test
    public void addAll(){
        List<Number> integerList = new ArrayList<>();
        integerList.add(100);
        integerList.add(200);
        integerList.add(null);

        assertTrue(linkedList.addAll(integerList));

        System.out.println(linkedList);
    }

    @Test
    public void copy(){
        List<Number> integerList = new ArrayList<>();
        assertTrue(linkedList.copy(integerList));

        System.out.println(integerList);
    }

    @Test(expected = NullPointerException.class)
    public void nullTest(){
        linkedList.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalTest(){
        assertTrue(linkedList.add(1, 20));
    }
}