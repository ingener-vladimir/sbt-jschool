package com.sbt.jschool.lesson11.factorial;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс для рассчета факториала каждого числа из файла
 *
 * @version 0.1
 * @autor Саньков Владимир
 */

public class FactorialProcessor {
    /**
     * Обработчик файла
     */
    private final FileWorker fileWorker;

    /**
     *
     * @param fileName
     */
    public FactorialProcessor(String fileName) {
        this.fileWorker = new FileWorker(fileName);
    }

    /**
     * Расчитывает факториал числа. Расчет каждого числа происходит в отдельном потоке
     */
    public void calculateFactorial() {
        String string = null;
        try {
            string = fileWorker.read();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        List<Integer> integerList = Arrays.asList(string.split(" ")).stream()
                .map(value -> Integer.parseInt(value))
                .collect(Collectors.toList());

        for (int i = 0; i < integerList.size(); i++) {
            int finalI = i;
            Thread thread = new Thread(() -> factorial(integerList.get(finalI)));
            thread.start();
        }
    }

    /**
     * Расчет факториала
     * @param integer Результат расчета
     */
    private void factorial(int integer) {
        BigInteger r = BigInteger.valueOf(1);
        for (int i = 2; i <= integer; ++i) {
            r = r.multiply(BigInteger.valueOf(i));
        }
        System.out.println("The factorial of number " + integer + " = " + r + "\n"
                + "He has been calculated in the " + Thread.currentThread().getName() + "\n"
                + "---------------------------------");
    }
}
