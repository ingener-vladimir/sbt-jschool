package com.sbt.jschool.lesson9.services;

import com.sbt.jschool.lesson9.interfaces.IService;

/**
 * Класс, реализующий интерфейс IService
 * Тестовый сервис
 * @version 0.1
 * @autor Саньков Владимир
 */
public class ServiceImpl implements IService {
    /**
     * Имя
     */
    private String name;
    /**
     * Значение
     */
    private int value;

    /**
     * @see IService
     */
    @Override
    public double doHardWork(String name, int value) {
        this.name = name;
        this.value = value;
        System.out.println("name: " + name + " " + "value " + value);
        return 10.0 + value;
    }
}
