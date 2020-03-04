package com.sbt.jschool.lesson1;

import java.util.Scanner;

public class Exercise2019 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int value = 0;

        label: for (int i = 1; i < Integer.MAX_VALUE; i++) {
            for (int j = 0; j < i; j++) {
                value++;
                if(value == number) {
                    System.out.println(i);
                    break label;
                }
            }
        }
    }
}
