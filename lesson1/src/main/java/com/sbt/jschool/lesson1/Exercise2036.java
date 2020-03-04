package com.sbt.jschool.lesson1;

import java.util.Scanner;

public class Exercise2036 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sizeArray = scanner.nextInt();

        String[] arr = new String[sizeArray];
        for (int i = 0; i < sizeArray; i++) {
            arr[i] = scanner.next();
        }

        for (String str : arr) {
            String string = str.replaceAll("[eyuioa+]{3,}?", "");
            if(str.length() != string.length())
                continue;
            System.out.println(str);
        }
    }
}
