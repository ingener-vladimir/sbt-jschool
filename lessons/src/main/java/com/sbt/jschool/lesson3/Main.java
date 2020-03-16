package com.sbt.jschool.lesson3;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LinkedList<Number> linkedList = new LinkedList<>();
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);
        linkedList.add(40);
        linkedList.add(50);

        linkedList.add(3, 35);
        System.out.println(linkedList);

        linkedList.remove(2);
        System.out.println(linkedList);

        List<Number> integ = new ArrayList<>();
        linkedList.copy(integ);
        System.out.println(linkedList);
        System.out.println(integ);

        List<Number> integers = new ArrayList<>();
        integers.add(100);
        integers.add(200);
        integers.add(300);
        linkedList.addAll(integers);
        System.out.println(linkedList);
    }
}
