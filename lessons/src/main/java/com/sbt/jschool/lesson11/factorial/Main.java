package com.sbt.jschool.lesson11.factorial;

public class Main {
    public static void main(String[] args) {
        FactorialProcessor factorialProcessor = new FactorialProcessor("temp.txt");
        factorialProcessor.calculateFactorial();
    }
}
