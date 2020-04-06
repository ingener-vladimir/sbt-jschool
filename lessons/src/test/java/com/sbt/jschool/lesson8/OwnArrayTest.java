package com.sbt.jschool.lesson8;

import org.junit.*;

import static org.junit.Assert.*;

import java.util.Iterator;

/**
 * Тест собственного итератора
 *
 * @version 0.1
 * @autor Саньков Владимир
 */
public class OwnArrayTest {
    OwnArray<Integer> ownArray;

    @Before
    public void before() {
        ownArray = new OwnArray<>(5);
        ownArray.addElement(0, 10);
        ownArray.addElement(1, 20);
        ownArray.addElement(2, 30);
        ownArray.addElement(3, 40);
        ownArray.addElement(4, 50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_illegal_argument_exception_when_remove_element_from_array() {
        Iterator<Integer> iterator = ownArray.iterator();
        iterator.remove();
        iterator.remove();
        iterator.remove();
        iterator.remove();
        iterator.remove();
        iterator.remove();
    }

    @Test
    public void test_assert_references_in_iterator() {
        Iterator<Integer> iterator = ownArray.iterator();
        if (iterator.hasNext()) {
            assertSame("Check an equality references", ownArray.getElement(0), iterator.next());
        }
    }

    @Test
    public void test_assert_values_in_iterator() {
        Iterator<Integer> iterator = ownArray.iterator();
        if (iterator.hasNext()) {
            assertEquals("Check an equality values", ownArray.getElement(0).intValue(), iterator.next().intValue());
        }
    }

    @Test
    public void test_print_elements_in_iterator() {
        Iterator<Integer> iterator = ownArray.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @After
    public void after() {
        ownArray = null;
    }
}