package com.sbt.jschool.lesson9.interfaces;

/**
 * Интерфейс хранения данных
 * @version 0.1
 * @autor Саньков Владимир
 */
public interface IRetriever {
    /**
     * Поиск в хранилище
     * @param hash Хэш-код для поиска
     * @return результат
     */
    boolean find(long hash);

    /**
     * Добавление элемента в хранилище
     * @param hash Хэш-код для вставки
     * @param object объект
     * @return результат
     */
    boolean add(long hash, Object object);

    /**
     * Получить объект из хранилища
     * @param hash Хэш-код для получения
     * @return объект
     */
    Object get(long hash);
}
