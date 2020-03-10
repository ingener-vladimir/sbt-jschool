package com.sbt.jschool.lesson1;

import java.util.Scanner;

public class Exercise2028 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sizeArray = scanner.nextInt();
        int[] arrayCount = new int[5];

        int[] arr = new int[sizeArray];
        for (int i = 0; i < sizeArray; i++) {
            arr[i] = scanner.nextInt();
            if(arr[i] < 0 || arr[i] > 4)
                continue;
            arrayCount[arr[i]]++;
        }

        for (int i = 0; i < arrayCount.length; i++) {
            if(arrayCount[i] != 0)
                System.out.println(i + " " + arrayCount[i]);
        }
    }
}
