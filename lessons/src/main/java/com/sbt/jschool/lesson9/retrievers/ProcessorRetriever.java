package com.sbt.jschool.lesson9.retrievers;

import com.sbt.jschool.lesson9.annotations.*;
import com.sbt.jschool.lesson9.interfaces.IRetriever;

import java.lang.reflect.*;
import java.util.*;

/**
 * Класс для работы с хранилищами
 * @version 0.1
 * @autor Саньков Владимир
 */
public class ProcessorRetriever {
    /**
     * Объект хранилища
     */
    private IRetriever retriever;
    /**
     * Текущий метод
     */
    private Method method;
    /**
     * Список с параметрами для уникальности
     */
    private List<Object> list = new ArrayList<>();
    /**
     * Рутовая директория
     */
    private String rootDir;
    /**
     * Нужно ли архивировать
     */
    private boolean isZip;

    /**
     * Коснтруктор
     * @param rootDir Рутовая директория
     */
    public ProcessorRetriever(String rootDir) {
        this.rootDir = rootDir;
    }

    /**
     * Плучить результат выполнения метода
     * @param object Объект, на котором выполняется метод
     * @param method Метод
     * @param args Аргументы метода
     * @return Результат выполнения
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object getResultMethod(Object object, Method method, Object[] args){
        parseValuesFromAnnotation(method, args);

        long hash = 0L;
        for (Object obj : list)
            hash += obj.hashCode();

        if (!retriever.find(hash)) {
            try {
                retriever.add(hash, method.invoke(object, args));
            } catch (IllegalAccessException e) {
                System.out.println("Error invoke the method with Cache annotation");
            } catch (InvocationTargetException e) {
                System.out.println("Exception thrown by an invoked method or constructor with Cache annotation");
            }
        }
        else
            System.out.println("Get cached returned value");

        return retriever.get(hash);
    }

    /**
     * Распарсить данные из аннотации
     * @param method Метод
     * @param args Аргументы метода
     */
    private void parseValuesFromAnnotation(Method method, Object[] args) {
        Cache cache = method.getAnnotation(Cache.class);
        CacheType cacheType = cache.cacheType();

        if(!method.equals(this.method)){
            this.method = method;
            isZip = cache.zip();
            if (cacheType == CacheType.JVM) {
                retriever = new RetrieverMemory();
            }
            else if (cacheType == CacheType.FILE) {
                retriever = new RetrieverFile(this.rootDir, isZip);
            }
        }

        Class[] classes = cache.uniqueArg();
        if (classes.length != 0) {
            for (Object obj : args) {
                for (Class cl : classes) {
                    if (cl == obj.getClass()) {
                        list.add(obj);
                        break;
                    }
                }
            }
        } else
            list = List.of(args);
    }
}
