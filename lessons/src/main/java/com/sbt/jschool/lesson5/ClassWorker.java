package com.sbt.jschool.lesson5;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

/**
 * Класс для получения информации о классе
 *
 * @version 0.1
 * @autor Саньков Владимир
 */
public class ClassWorker {
    Class clazz;

    /**
     * Конструктор с параметрами
     *
     * @param clazz типа Class
     */
    public ClassWorker(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * Конструктор с параметрами
     *
     * @param nameClass строка с именем класса вида "com.lesson.java.Test"
     * @throws ClassNotFoundException если класс не обнаружен
     */
    public ClassWorker(String nameClass) throws ClassNotFoundException {
        this.clazz = Class.forName(nameClass);
    }

    /**
     * Печать всех методов класса(родительские, приватные)
     */
    public void printMethods() {
        System.out.println("Class's methods:");
        Set<Method> lists = new HashSet<>(Arrays.asList(clazz.getMethods()));
        for (Method method : clazz.getDeclaredMethods())
            lists.add(method);

        for (Method method : lists) {
            System.out.println("name - " + method.getName());
            System.out.println("return type - " + method.getReturnType().getName());
            System.out.println("count parameters - " + method.getParameterCount());
            System.out.println("parameters:");
            for (Parameter parameter : method.getParameters()) {
                System.out.println("name - " + parameter.getName());
                System.out.println("type - " + parameter.getType().getName());
            }
            System.out.println("modifier:");
            System.out.println(Modifier.toString(method.getModifiers()));
            System.out.println("exceptions:");
            for (Class exception : method.getExceptionTypes()) {
                System.out.println("name - " + exception.getName());
            }
            System.out.println("annotations:");
            for (Annotation annotation : method.getAnnotations()) {
                System.out.println("name - " + annotation.annotationType().getName());
            }
            System.out.println();
        }
    }

    /**
     * Печать всех методов get/set класса
     */
    public void printGettersSetters() {
        System.out.println("Getters and Setters:");

        Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            String nameField = fields[i].getName();
            if (haveUpperCaseSecondSymbol(nameField) == false)
                nameField = fields[i].getName().replaceFirst(fields[i].getName().substring(0, 1),
                        fields[i].getName().substring(0, 1).toUpperCase());
            try {
                Method methodGet = clazz.getMethod("get" + nameField);
                System.out.println(methodGet.getName());
            } catch (NoSuchMethodException nsme) {
                nsme.getMessage();
            }

            try {
                Method methodSet = clazz.getMethod("set" + nameField, fields[i].getType());
                System.out.println(methodSet.getName());
            } catch (NoSuchMethodException nsme) {
                nsme.getMessage();
            }
        }
    }

    /**
     * Проверить имя поля класса на наличие заглавной буквы. стоящей на втором месте.
     * К примеру, если имя поля aPit, то IDE сгенерирует set/get с именами
     * setaPit и getaPit соответсвенно
     */
    private boolean haveUpperCaseSecondSymbol(String str) {
        if (str.length() > 1) {
            return Character.isUpperCase(Character.valueOf(str.charAt(1)));
        }
        return false;
    }

    /**
     * Проверить что все String константы имеют значение, равное их имени
     *
     * @throws IllegalAccessException
     */
    public void checkFieldsOnEqualityNameAndValue() {
        for (Field field : clazz.getDeclaredFields()) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) && Modifier.isFinal(mod) && field.getType().getName().equals("java.lang.String")) {
                String name = field.getName();
                String value = "";
                try {
                    value = (String) field.get(name);
                } catch (IllegalAccessException iae) {
                    System.out.println("Error access to value of field");
                }
                if (name.equals(value))
                    System.out.println(name + " == " + value);
                else
                    System.out.println(name + " != " + value);
            } else
                System.out.println("Type of field isn't static or final or hasn't type \"String\"");
        }
    }
}
