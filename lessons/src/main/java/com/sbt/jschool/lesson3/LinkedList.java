package com.sbt.jschool.lesson3;

/**
 * Класс связного списка
 *
 * @autor Саньков Владимир
 * @version 0.1
 */

import java.util.*;
import java.util.function.Consumer;

public class LinkedList<T> implements Iterable<T> {

    /** Размерность спсика */
    int size;
    /** Следующий элемент */
    private Node<T> head;
    /** Предыдущий элемент */
    private Node<T> tail;

    /**
     * Добавить элемент в список
     * @param element значение
     * @return результат добавления
     */
    public boolean add(T element) {
        if (element == null) {
            throw new NullPointerException("trying add a null pointer");
        }

        final Node<T> x = tail;
        Node<T> node = new Node<>(element, x, null);
        this.tail = node;

        if (x == null) {
            this.head = node;
        } else {
            x.next = node;
        }
        size++;
        return true;
    }

    /**
     * Добавить элемент в список по индексу
     * @param index индекс
     * @param element значение
     * @return результат добавления
     */
    public boolean add(int index, T element) {
        checkIllegalIndex(index);

        if (index == size) {
            add(element);
        } else {
            final Node<T> node = getNodeByIndex(index);
            Node<T> newNode = new Node<>(element, node.previous, node);
            if (node.previous != null)
                node.previous.next = node;
            else
                this.head = newNode;
            node.previous = newNode;
            size++;
        }

        return true;
    }

    /**
     * Получить элемент по индексу
     * @param index индекс
     * @return элемент
     */
    public T get(int index) {
        checkIllegalIndex(index);

        return getNodeByIndex(index).value;
    }

    /**
     * Удалить элемент по индексу
     * @param index индекс
     * @return удаляемый элемент
     */
    public T remove(int index) {
        checkIllegalIndex(index);

        Node<T> node = getNodeByIndex(index);

        final T element = node.value;
        final Node<T> next = node.next;
        final Node<T> prev = node.previous;

        if (prev == null) {
            this.head = next;
        } else {
            prev.next = next;
            node.previous = null;
        }

        if (next == null) {
            this.tail = prev;
        } else {
            next.previous = prev;
            node.next = null;
        }

        node.value = null;
        size--;
        return element;
    }

    /**
     * Добавить все из коллекции coll в список
     * @param coll коллекция
     * @return результат добавления
     */
    public boolean addAll(Collection<? extends T> coll) {
        Iterator<? extends T> iterator = coll.iterator();
        while(iterator.hasNext()){
            add(iterator.next());
        }

        return true;
    }

    /**
     * Копировать список в коллекцию
     * @param coll коллекция
     * @return результат копирования
     */
    public boolean copy(Collection<? super T> coll) {
        Iterator<T> iterator = iterator();
        while(iterator.hasNext()){
            coll.add(iterator.next());
        }
        return true;
    }

    /**
     * Получить узел по индексу
     * @param index индекс
     * @return узел
     */
    private Node<T> getNodeByIndex(int index) {
        Node<T> node = null;
        int halfSize = size / 2;
        if (index <= halfSize) {
            node = this.head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = this.tail;
            for (int i = size; i > index + 1; i--) {
                node = node.previous;
            }
        }

        return node;
    }

    /**
     * Проверка выхода за границы размера списка
     * @param index индекс
     * @return результат проверки
     */
    private void checkIllegalIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index must be less a size");
        }
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator = new Iterator<>() {

            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                final T val = current.value;
                current = current.next;
                return val;
            }
        };
        return iterator;
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    @Override
    public String toString() {
        String str = "LinkedList: ";
        Iterator<T> iterator = iterator();

        while(iterator.hasNext()){
            str += iterator.next() + " ";
        }

        return str;
    }

    /**
     Класс узла списка
     */
    private static class Node<T> {
        /** Значение */
        private T value;
        /** Следующий узел */
        private Node<T> next;
        /** Предыдущий узел */
        private Node<T> previous;

        /**
         * Конструктор класса
         * @param value значение
         * @param prev ссылка на предудущий элемент
         * @param next ссылка на следующий элемент
         */
        Node(T value, Node<T> prev, Node<T> next) {
            this.value = value;
            this.previous = prev;
            this.next = next;
        }
    }
}
