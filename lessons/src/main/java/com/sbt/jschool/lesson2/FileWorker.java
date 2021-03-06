package com.sbt.jschool.lesson2;

/**
 * Класс для работы с файлом
 *
 * @version 0.1
 * @autor Саньков Владимир
 */

import java.io.*;
import java.util.*;

public class FileWorker {
    List<String> listWords = new ArrayList<>();

    /**
     * Конструктор - создание нового обработчика файла
     *
     * @param fileName - имя файла
     * @throws IOException
     */
    public FileWorker(String fileName) throws IOException {
        readWords(fileName);
    }

    /**
     * Прочитать слова из файла
     *
     * @param fileName - имя файла
     * @throws IOException
     */
    private void readWords(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while (true) {
                try {
                    if (bufferedReader.ready()) {
                        String str = bufferedReader.readLine().replaceAll("[^A-Za-z0-9]", " ");
                        listWords.addAll(Arrays.asList(str.split(" ")));
                    } else
                        break;
                } catch (IOException e) {
                    System.out.println("Error reading from file");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Подсчитать количество различных слов в файле
     *
     * @return количество различных слов
     */
    public int countDifferentWords() {
        Set<String> stringSet = new LinkedHashSet<>(listWords);
        return stringSet.size();
    }

    /**
     * Подсчитать и вывести на экран сколько раз каждое слово встречается в файле
     */
    public void countRepeated() {
        Map<String, Integer> countRepeated = new TreeMap<>();

        for (String word : listWords) {
            if (!countRepeated.containsKey(word)) {
                countRepeated.put(word, 0);
            }
            countRepeated.put(word, countRepeated.get(word) + 1);
        }


        countRepeated.forEach((k, v) ->
                System.out.println("Word " + "\"" + k + "\"" + " repeats " + v + " times"));
    }

    /**
     * Вывести на экран все строки файла в обратном порядке
     */
    public void reverseWords() {
        for (int i = listWords.size() - 1; i >= 0; i--) {
            System.out.println(listWords.get(i));
        }
    }

    /**
     * Вывести на экран строки, номера которых задаются пользователем в произвольном порядке
     */
    public boolean writeRandomWord(int numberWord) {
        if (listWords.isEmpty()) {
            System.out.println("List words is empty");
            return false;
        }

        if (numberWord < 0) {
            System.out.println("Number must be positive");
            return false;
        }

        if (numberWord >= listWords.size()) {
            System.out.println("Number must be less a size of collection");
            return false;
        }

        System.out.println("The word is " + "\"" + listWords.get(numberWord) + "\"");
        return true;
    }

    /**
     * Вывести на экран список различных слов файла, отсортированный по возрастанию их длины (компаратор сначала по длине слова, потом по тексту)
     */
    public void sortByLength() {
        Collections.sort(listWords);
        Collections.sort(listWords, new Comparator<String>() {
            @Override
            public int compare(String t, String t1) {
                return Integer.compare(t.length(), t1.length());
            }
        });
//        Collections.sort(listWords, (u1, u2) ->
//                u1.length() == u2.length() ? 0 : u1.length() > u2.length() ? 2 : -1
//        );

        listWords.forEach(System.out::println);


    }
}
