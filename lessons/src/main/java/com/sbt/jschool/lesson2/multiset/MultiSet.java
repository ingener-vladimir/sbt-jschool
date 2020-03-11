package com.sbt.jschool.lesson2.multiset;

/**
 * Структуру данных, которая умеет хранить мультимножество натуральных чисел
 *
 * @version 0.1
 * @autor Саньков Владимир
 */

import java.util.*;

public class MultiSet{
    /** Массив элементов */
    Integer[] elements;
    /** Размер массива */
    int size;
    /**
     * Конструктор по-умолчанию
     */
    public MultiSet() {
        this.elements = new Integer[]{};
    }

    /**
     * Конструктор с апарметром размера массива
     *
     * @param size  - размер
     */
    public MultiSet(int size) {
        if (size > 0) {
            this.elements = new Integer[size];
            this.size = size;
        } else if (size == 0) {
            this.elements = new Integer[]{};
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + size);
        }
    }

    /**
     * Вставить элемент
     *
     * @param element  - элемент для вставки
     */
    public boolean addElement(Integer element){
        if(this.elements.length == size){
            this.elements = grow();
        }
        this.elements[size] = element;
        this.size = this.size + 1;
        return true;
    }

    /**
     * Увеличить размер массива и вернуть новый
     *
     * @return новый массив
     */
    private Integer[] grow() {
        return elements = Arrays.copyOf(elements, (elements.length * 3) / 2 + 1);
    }

    /**
     * Изменить размер массива до фактического
     */
    private void trimToSize() {
        if (size < elements.length) {
            elements = (size == 0)
                    ? new Integer[]{}
                    : Arrays.copyOf(elements, size);
        }
    }

    /**
     * Удалить минимальный и вернуть его
     *
     * @return удаленный элемент
     */
    public int deleteMin(){
        int minIndex = findMin();
        if(minIndex < 0){
            System.out.println("Array is empty!");
            return minIndex;
        }

        int min = elements[minIndex];
        final int newSize;
        if ((newSize = size - 1) > minIndex)
            System.arraycopy(elements, minIndex + 1, elements, minIndex, newSize - minIndex);
        elements[size = newSize] = null;

        return min;
    }

    /**
     * Найти минимальный элемент
     *
     * @return минимальный элемент
     */
    private int findMin(){
        int minValue = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < size; i++) {
            if(elements[i] < minValue){
                minValue = (int)elements[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    @Override
    public String toString() {
        this.trimToSize();
        return "MultiSet{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }
}
