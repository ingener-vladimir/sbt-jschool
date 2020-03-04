package com.sbt.jschool.lesson1;

import java.util.Scanner;

public class Exercise2033 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sizeArray = scanner.nextInt();

        int[] arr = new int[sizeArray];
        for (int i = 0; i < sizeArray; i++) {
            arr[i] = scanner.nextInt();
        }

        for (int i = sizeArray - 1; i >= 0; i--) {
            if (arr[i] == 9 && i != 0) {
                arr[i] = 0;
            } else {
                arr[i]++;
                break;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}

