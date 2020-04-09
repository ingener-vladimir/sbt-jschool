package com.sbt.jschool.lesson9.proxy;

import com.sbt.jschool.lesson9.annotations.Cache;
import com.sbt.jschool.lesson9.retrievers.ProcessorRetriever;

import java.io.FileNotFoundException;
import java.lang.reflect.*;
import java.util.*;

/**
 * Класс, реализующий интерфейс InvocationHandler
 * @version 0.1
 * @autor Саньков Владимир
 */
public class CacheHandler implements InvocationHandler {
    /**
     * Объект
     */
    private Object object;
    /**
     * Словарь с методом и объектом ProcessorRetriever
     */
    private Map<Method, ProcessorRetriever> mapProcessorRetriever = new HashMap<>();
    /**
     * Рутовая директория
     */
    private String rootDir;

    /**
     * Конструктор с парметром
     * @param object Объект
     */
    public CacheHandler(Object object) {
        this.object = object;
    }

    /**
     * Конструктор с параметрами
     * @param object Объект
     * @param rootDir Рутовая директория
     */
    public CacheHandler(Object object, String rootDir) {
        this.object = object;
        this.rootDir = rootDir;
    }

    /**
     * Переопределенный метод, который вызывает метод объекта object
     * @param proxy Прокси
     * @param method Метод
     * @param args Аргументы метода
     * @return Результат выполнения метода
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args){
        if(!method.isAnnotationPresent(Cache.class)) {
            try {
                return method.invoke(object, args);
            } catch (IllegalAccessException e) {
                System.out.println("Error invoke the method");
            } catch (InvocationTargetException e) {
                System.out.println("Exception thrown by an invoked method or constructor with Cache annotation");
            }
        }

        if(!mapProcessorRetriever.containsKey(method)){
            mapProcessorRetriever.put(method, new ProcessorRetriever(rootDir));
        }

        return mapProcessorRetriever.get(method).getResultMethod(object, method, args);
    }
}
