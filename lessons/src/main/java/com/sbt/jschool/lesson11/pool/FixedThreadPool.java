package com.sbt.jschool.lesson11.pool;

import com.sbt.jschool.lesson11.interfaces.ThreadPool;
import com.sbt.jschool.lesson11.tasks.TaskWorker;

import java.util.LinkedList;

/**
 * Класс, реализующий пулл потоков фиксированного размера
 *
 * @version 0.1
 * @autor Саньков Владимир
 */

public class FixedThreadPool implements ThreadPool {
    /**
     * Количество потоков
     */
    private final int countThreads;

    /**
     * Список задач
     */
    private final LinkedList<Runnable> workQueue = new LinkedList<>();

    /**
     * Констркутор с параметрами
     * @param countThreads Количество потоков
     */
    public FixedThreadPool(int countThreads) {
        if(countThreads < 0)
            throw new IllegalArgumentException();

        this.countThreads = countThreads;
    }

    /**
     * @see ThreadPool
     */
    @Override
    public void start() {
        for (int i = 0; i < countThreads; ++i) {
            new Thread(new TaskWorker(workQueue, false)).start();
        }
    }

    /**
     * @see ThreadPool
     */
    @Override
    public void execute(Runnable command) {
        synchronized (workQueue) {
            workQueue.addLast(command);
            workQueue.notifyAll();
        }
    }
}
