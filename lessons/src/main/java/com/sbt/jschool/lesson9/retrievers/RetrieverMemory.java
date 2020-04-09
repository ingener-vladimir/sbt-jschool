package com.sbt.jschool.lesson9.retrievers;

import com.sbt.jschool.lesson9.interfaces.IRetriever;
import java.util.*;

/**
 * Класс, реализующий интерфейс IRetriever
 * Служит для хоанения кэшированных данных в файле
 * @version 0.1
 * @autor Саньков Владимир
 */
public class RetrieverMemory implements IRetriever {
    /**
     * Словарь для хранения кэшированных данных
     */
    private Map<Long, Object> map = new HashMap<>();

    /**
     * @see IRetriever
     */
    @Override
    public boolean find(long hash) {
        return map.containsKey(hash);
    }

    /**
     * @see IRetriever
     */
    @Override
    public boolean add(long hash, Object object) {
        map.put(hash, object);
        return true;
    }

    /**
     * @see IRetriever
     */
    @Override
    public Object get(long hash) {
        return map.get(hash);
    }
}
