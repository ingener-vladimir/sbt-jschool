package com.sbt.jschool.lesson2.multiset;

import org.junit.*;

import static org.junit.Assert.*;

public class MultiSetTest {
    MultiSet multiSet;

    @Before
    public void create(){
        multiSet = new MultiSet();
        multiSet.addElement(12);
        multiSet.addElement(1);
        multiSet.addElement(15);
        multiSet.addElement(-100);
        multiSet.addElement(0);
    }

    @Test
    public void addElement() {
        assertTrue(multiSet.addElement(90));
        assertTrue(multiSet.addElement(-154));
    }

    @Test
    public void deleteMin() {
        assertEquals(multiSet.deleteMin(), -100);
        assertEquals(multiSet.deleteMin(), -0);
    }

    @Test
    public void deleteMinEmptyArray() {
        assertEquals(multiSet.deleteMin(), -1);
    }
}