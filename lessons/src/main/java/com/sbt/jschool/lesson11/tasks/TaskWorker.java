package com.sbt.jschool.lesson11.tasks;

import java.util.LinkedList;

/**
 * Класс, имплементирующий Runnable и реализуйщий логику для работы в отдельном потоке
 *
 * @version 0.1
 * @autor Саньков Владимир
 */

public class TaskWorker implements Runnable {
    /**
     * Флаг окончания работы потока
     */
    private boolean isTerminate;

    /**
     * Список задач
     */
    private final LinkedList<Runnable> workQueue;

    /**
     * Конструктор с параметрами
     * @param runnables Список задач
     * @param isTerminate Флаг окончания работы потока
     */

    public TaskWorker(LinkedList<Runnable> runnables, boolean isTerminate) {
        workQueue = runnables;
        this.isTerminate = isTerminate;
    }

    @Override
    public void run() {
        Runnable r;
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (workQueue) {
                while (workQueue.isEmpty()) {
                    try {
                        workQueue.wait();
                    } catch (InterruptedException ignored) {
                        System.out.println("Thread " + Thread.currentThread().getName() + " interrupted");
                    }
                }

                r = workQueue.removeFirst();
            }
            if (r != null)
                r.run();

            if(isTerminate){
                Thread.currentThread().interrupt();
            }
        }
    }
}
