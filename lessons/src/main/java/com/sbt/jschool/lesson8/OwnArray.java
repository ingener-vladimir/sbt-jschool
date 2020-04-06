package com.sbt.jschool.lesson8;

import java.util.*;

/**
 * Класс для хранения массива с собственным итератором
 *
 * @version 0.1
 * @autor Саньков Владимир
 */
public class OwnArray<E> {
    /**
     * Размер массива
     */
    private int size;
    /**
     * Массив
     */
    private Object[] array;

    /**
     * Конструктор с параметром
     * @param size Размер массива
     */
    public OwnArray(int size) {
        array = new Object[size];
        this.size = size;
    }

    /**
     * Удалить элемент из массива
     * @param index Индекс элемента
     * @exception IllegalArgumentException
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }

        array[index] = null;
        size--;
    }

    /**
     * Функция возвращает элемент по индексу
     * Она тестовая, поэтому никакие проверку не вклчючены
     */
    public E getElement(int index) {
        return (E) array[index];
    }

    /**
     * Функция возвращает элемент по индексу
     * Она тестовая, поэтому никакие проверку не вклчючены
     */
    public void addElement(int indx, E element) {
        array[indx] = element;
    }

    @Override
    public String toString() {
        return "OwnArray{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    /**
     * Получить итератор
     * @return итератор
     */
    public Iterator<E> iterator() {
        return new OwnIterator();
    }

    private class OwnIterator<E> implements Iterator {
        /**
         * Текущий индекс элемента
         */
        private int position;
        /**
         * Индекс последнего возвратимого
         */
        private int lastReturned;


        @Override
        public boolean hasNext() {
            return this.position != size;
        }

        @Override
        public Object next() {
            int ind = position;
            if (ind >= size)
                throw new NoSuchElementException();

            position = ind + 1;
            lastReturned = ind;
            return array[lastReturned];
        }

        @Override
        public void remove() {
            position = lastReturned;
            OwnArray.this.remove(lastReturned);
        }
    }
}

