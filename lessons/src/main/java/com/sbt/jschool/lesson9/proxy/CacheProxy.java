package com.sbt.jschool.lesson9.proxy;

import com.sbt.jschool.lesson9.interfaces.IService;

import java.lang.reflect.Proxy;

/**
 * Класс-прокси
 * @version 0.1
 * @autor Саньков Владимир
 */
public class CacheProxy {
    /**
     *
     */
    private String path;

    /**
     *
     * @param path
     */
    public CacheProxy(String path) {
        this.path = path;
    }

    /**
     * Функция, которая принимает ссылку на сервис и возвращает кешированную версию этого сервиса
     * @param service Сервис
     * @return Кэшированная версия
     */
    public IService cache(IService service){
        IService iService = (IService)Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                service.getClass().getInterfaces(),
                new CacheHandler(service, this.path)
        );

        return iService;
    }
}
