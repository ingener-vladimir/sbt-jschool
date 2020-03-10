package com.sbt.jschool.lesson1;

import java.util.Scanner;

public class Exercise2003 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int sum = 0;

        for (int i = 0; i < count; i++) {
            int value = scanner.nextInt();
            if(i % 2 == 0)
                sum += value;
            else
                sum -= value;
        }

        System.out.println(sum);
    }
}
