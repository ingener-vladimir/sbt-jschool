package com.sbt.jschool.lesson2.multiset;

public class Main {
    public static void main(String[] args) {
        MultiSet multiSet = new MultiSet();
        multiSet.addElement(10);
        multiSet.addElement(20);
        multiSet.addElement(30);
        multiSet.addElement(40);
        multiSet.addElement(50);

        System.out.println(multiSet.deleteMin());
        System.out.println(multiSet.toString());
    }
}
