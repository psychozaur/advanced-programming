package com.rybicki.marcin.programming.advanced.array_addition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {

        Object lock = new Object();

        long[] array = {1,2,3,4,5,6,7,8,9,10};
        int numberOfThreads = 3;

        List<ArrayAdditionThread> threadList = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++){
            threadList.add(new ArrayAdditionThread(array,numberOfThreads,i,lock));
        }

        ExecutorService workers = Executors.newFixedThreadPool(numberOfThreads);

        for (ArrayAdditionThread worker : threadList){
            workers.execute(worker);
        }

        workers.shutdown();
    }
}
