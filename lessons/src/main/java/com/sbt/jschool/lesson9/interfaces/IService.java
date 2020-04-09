package com.sbt.jschool.lesson9.interfaces;

import com.sbt.jschool.lesson9.annotations.*;

/**
 * Интерфейс сервиса
 * @version 0.1
 * @autor Саньков Владимир
 */
public interface IService{
    /**
     * Тестовая функция
     * @param name имя
     * @param value значение
     * @return
     */
    @Cache(cacheType = CacheType.FILE)
    double doHardWork(String name, int value);
}
