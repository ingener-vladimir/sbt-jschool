package com.sbt.jschool.lesson2;

import java.io.IOException;
import java.util.Scanner;

public class Exercise1 {

    public static void main(String[] args) throws IOException {
        FileWorker fileWorker = new FileWorker("words.txt");

        // Подсчитайте количество различных слов в файле
        System.out.println("Count different words in the file = " + fileWorker.countDifferentWords());

        // Подсчитайте и выведите на экран сколько раз каждое слово встречается в файле
        fileWorker.countRepeated();

        // Выведите на экран все строки файла в обратном порядке
        fileWorker.reverseWords();

//        // Выведите на экран строки, номера которых задаются пользователем в произвольном порядке
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextInt()) {
//            int number = scanner.nextInt();
//            fileWorker.writeRandomWord(number);
//        }

        // Выведите на экран список различных слов файла, отсортированный по возрастанию их длины (компаратор сначала по длине слова, потом по тексту)
        fileWorker.sortByLength();
    }
}
