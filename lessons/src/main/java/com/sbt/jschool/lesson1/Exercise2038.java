package com.sbt.jschool.lesson1;

import java.util.Scanner;

public class Exercise2038 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();

        String[] newString = string.replaceAll("[^A-Za-z]", " ").split(" ");

        int maxSize = 1;
        for(String str : newString){
            if(str.length() > maxSize)
                maxSize = str.length();
        }
        System.out.println(maxSize);
    }
}
