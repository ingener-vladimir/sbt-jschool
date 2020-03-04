package com.sbt.jschool.lesson1;

import java.util.Scanner;

public class Exercise2020 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countNumber = scanner.nextInt();

        int maxRepeats = 0;
        int prevValue = 1;
        int counterRepeats = 0;

        for (int i = 0; i < countNumber; i++) {
            int value = scanner.nextInt();
            if (value == prevValue) {
                counterRepeats++;
                prevValue = value;
                continue;
            }
            if (counterRepeats > maxRepeats) {
                maxRepeats = counterRepeats;
                counterRepeats = 0;
            } else if (counterRepeats == maxRepeats)
                break;
        }

        System.out.println(prevValue + " " + maxRepeats);
    }
}
