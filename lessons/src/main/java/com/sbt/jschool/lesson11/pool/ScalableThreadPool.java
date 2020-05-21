package com.sbt.jschool.lesson11.pool;

import com.sbt.jschool.lesson11.interfaces.ThreadPool;
import com.sbt.jschool.lesson11.tasks.TaskWorker;

import java.util.*;

/**
 * Класс, реализующий пулл потоков с минимальным и максимальным количеством потоков.
 * Количество запущенных потоков может быть увеличено от минимального к максимальному,
 * если при добавлении нового задания в очередь нет свободного потока для исполнения этого задания
 *
 * @version 0.1
 * @autor Саньков Владимир
 */

public class ScalableThreadPool implements ThreadPool {
    /**
     * Минимальное количество потоков
     */
    private final int min;

    /**
     * Максимальное количество потоков
     */
    private final int max;

    /**
     * Список задач
     */
    private final LinkedList<Runnable> workQueue = new LinkedList<>();

    /**
     * Список потоков
     */
    private LinkedList<Thread> threadLinkedList = new LinkedList<>();

    /**
     * Конструктор с параметрами
     * @param min Минимальное количество потоков
     * @param max Максимальное количество потоков
     */
    public ScalableThreadPool(int min, int max) {
        if(max < min || min < 0 || max < 0)
            throw new IllegalArgumentException();

        this.min = min;
        this.max = max;
    }

    /**
     * @see ThreadPool
     */
    @Override
    public void start() {
        for (int i = 0; i < min; ++i) {
            Thread thread = new Thread(new TaskWorker(workQueue, false));
            threadLinkedList.add(thread);
            thread.start();
        }
    }

    /**
     * @see ThreadPool
     */
    @Override
    public void execute(Runnable command) {
        synchronized (workQueue) {
            workQueue.addLast(command);

            int size = workQueue.size();
            if (threadLinkedList.size() < max && size > threadLinkedList.size()) {
                for (int i = 0; i < (max - min); ++i) {
                    Thread thread = new Thread(new TaskWorker(workQueue, true));
                    threadLinkedList.add(thread);
                    thread.start();
                }
            }

            workQueue.notifyAll();
        }
        removeInterrupted();
    }

    /**
     * Удаляет завершенный поток из списка потоков
     */
    private void removeInterrupted() {
        Iterator<Thread> iterator = threadLinkedList.iterator();
        while(iterator.hasNext()){
            Thread next = iterator.next();
            if(next.getState() == Thread.State.TERMINATED){
                iterator.remove();
            }
        }
    }
}
