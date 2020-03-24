package com.sbt.jschool.lesson3;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LinkedList<Number> linkedList = new LinkedList<>();
        linkedList.add(null);
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
//
//        List<Number> integers = new ArrayList<>();
//        integers.add(100);
//        integers.add(200);
//        integers.add(300);
//        linkedList.addAll(integers);
//        System.out.println(linkedList);

//        List<Number> list = new ArrayList<>();
//
//        CollectionUtils.add(list, 0);
//        CollectionUtils.add(list, 0);
//        CollectionUtils.add(list, 30);
//
//        List<Number> list1 = new ArrayList<>();
//
//        CollectionUtils.add(list1, 10);
//        CollectionUtils.add(list1, 20);
//        CollectionUtils.add(list1, 40);
//        CollectionUtils.add(list1, 50);
//        CollectionUtils.add(list1, 80);
//        System.out.println(list);
//
//        System.out.println(CollectionUtils.indexOf(list, 10));
//
////        CollectionUtils.removeAll(list, list1);
////        System.out.println(list);
//
//        System.out.println(CollectionUtils.containsAll(list1, list));
//
//        System.out.println(CollectionUtils.containsAny(list, list1));
    }
}
