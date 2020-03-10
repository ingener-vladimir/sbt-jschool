package com.sbt.jschool.lesson1;

import java.util.Scanner;

public class Exercise2021 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sizeArray = scanner.nextInt();
        int maxValue = 1;

        int[] arr = new int[sizeArray];
        for (int i = 0; i < sizeArray; i++) {
            arr[i] = scanner.nextInt();
            if(arr[i] > maxValue)
                maxValue = arr[i];
        }

        int newMax = 1;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < sizeArray; j++) {
                if(arr[j] == maxValue)
                    arr[j] /= 2;

                if(arr[j] > newMax)
                    newMax = arr[j];
            }
            maxValue = newMax;
        }

        for (int i : arr){
            System.out.print(i + " ");
        }
    }
}
