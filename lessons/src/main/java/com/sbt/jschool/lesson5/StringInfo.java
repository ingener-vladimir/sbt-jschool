package com.sbt.jschool.lesson5;

/**
 * Класс для вывода информации о классе String
 *
 * @version 0.1
 * @autor Саньков Владимир
 */

import java.lang.reflect.*;
import java.util.*;

public class StringInfo {
    public static void main(String[] args) {
        Set<String> stringSet = new TreeSet<>();
        for (Field field : String.class.getDeclaredFields()) {
            for (String string : Modifier.toString(field.getModifiers()).split(" ")) {
                stringSet.add(string);
            }
        }
        System.out.println("Type modifiers:");
        stringSet.forEach(System.out::println);
        System.out.println();

        System.out.println("Package's name:");
        System.out.println(String.class.getPackageName());
        System.out.println();

        System.out.println("Hierarchy:");
        Class clazz = String.class.getSuperclass();
        while (clazz != null) {
            System.out.println("parent: " + clazz);
            clazz = clazz.getSuperclass();
        }
        System.out.println();

        System.out.println("Interfaces:");
        printInterface(String.class);
    }

    /**
     * Рекурсивная функция вывода на консоль интерфейсов класса
     * @param clazz Класс
     */
    static void printInterface(Class clazz) {
        Class[] arrayClasses = clazz.getInterfaces();

        for (Class clss : arrayClasses) {
            System.out.println(clss);
            printInterface(clss);
        }
    }
}
