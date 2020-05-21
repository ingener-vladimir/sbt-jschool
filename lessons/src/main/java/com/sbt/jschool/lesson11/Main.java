package com.sbt.jschool.lesson11;

import com.sbt.jschool.lesson11.interfaces.ThreadPool;
import com.sbt.jschool.lesson11.pool.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ThreadPool executor = new FixedThreadPool(4);
        executor.start();

//        for (int i = 0; i < 20; ++i) {
//            executor.execute(() -> {
//                System.out.println("Thread " + Thread.currentThread().getName() + " started");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        }

        //===========================================================//

        ThreadPool threadPool = new ScalableThreadPool(2, 6);
        threadPool.start();

        for (int i = 0; i < 16; ++i) {
            threadPool.execute(() -> {
                System.out.println("Thread " + Thread.currentThread().getName() + " started");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
