package com.sbt.jschool.lesson5.reflection;

import java.io.*;

/**
 * @version 0.1
 * @autor Саньков Владимир
 */

class Employer {
    private int age;
    private String name;
    private boolean isMan;
    private char code;

    public void create(String ball, long count) throws IOException {
        throw new IOException();
    }

    public boolean copy(int number) throws IOException {
        return false;
    }

    public synchronized static final String sort(int number) {
        return null;
    }
}

class Lawyer extends Employer {

    public byte getaPit() {
        return aPit;
    }

    public void setaPit(byte aPit) {
        this.aPit = aPit;
    }

    private byte aPit;
    public static final String MONDAY = "MONDAY";
    static final String FRIDAY = "FRIDAY";
    public static final String THURSDAY = "THURSDAy";

    @Override
    @Deprecated
    public boolean copy(int number){
        return true;
    }

    private char find(int index) {
        return 0;
    }
}

public class Main {

    public static void main(String[] args) {
        try {
            ClassWorker classWorker1 = new ClassWorker("com.sbt.jschool.lesson5.reflection.Lawyer");
            classWorker1.printMethods();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("This class does not exist");
        }

        Employer employer = new Lawyer();
        ClassWorker classWorker2 = new ClassWorker(employer.getClass());
        classWorker2.printMethods();

        ClassWorker classWorker3 = new ClassWorker(Lawyer.class);
        classWorker3.printMethods();
        classWorker3.printGettersSetters();
        classWorker3.checkFieldsOnEqualityNameAndValue();
    }
}
