package com.sbt.jschool.lesson1;

import java.util.Scanner;

public class Exercise2024 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sizeArray = scanner.nextInt();

        int[] arr = new int[sizeArray];
        for (int i = 0; i < sizeArray; i++) {
            arr[i] = scanner.nextInt();
        }

        int countElements = 0;
        for (int i = 0; i < sizeArray / 2; i++) {
            if(arr[i] != arr[sizeArray - i - 1]){
                countElements++;
            }
        }

        System.out.println(countElements);
    }
}
