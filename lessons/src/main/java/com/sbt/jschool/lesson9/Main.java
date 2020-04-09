package com.sbt.jschool.lesson9;

import com.sbt.jschool.lesson9.interfaces.IService;
import com.sbt.jschool.lesson9.proxy.CacheProxy;
import com.sbt.jschool.lesson9.services.ServiceImpl;

/**
 * @version 0.1
 * @autor Саньков Владимир
 */
public class Main {
    public static void main(String[] args) {
        CacheProxy cacheProxy = new CacheProxy("/home/ingener/IdeaProjects/sbt-jschool/lessons/src/main/java/com/sbt/jschool/lesson9/cachedFiles/");
        IService service = cacheProxy.cache(new ServiceImpl());
        service.doHardWork("ServiceImpl", 10);
        service.doHardWork("ServiceImpl", 20);
        service.doHardWork("ServiceImpl", 10);
        service.doHardWork("ServiceImpl", 20);
        service.doHardWork("ServiceImpl", 30);
    }
}
