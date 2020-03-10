package com.sbt.jschool.lesson1;

import java.util.Scanner;

public class Exercise2004 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        byte result = 0;

        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
            result = 1;

        System.out.println(result);
    }
}
