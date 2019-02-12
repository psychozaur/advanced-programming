package com.rybicki.marcin.programming.advanced.array_addition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        long[] array = new long[1000];

        for (int i = 0; i < array.length; i++){
            array[i] = i + 1;
        }
        int numberOfThreads = 50;

        List<ArrayAdditionThread> threadList = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++){
            threadList.add(new ArrayAdditionThread(array,numberOfThreads,i,lock,condition));
        }

        FinalAdditionThread finalCountdown = new FinalAdditionThread(threadList, lock,condition);

        ExecutorService workers = Executors.newFixedThreadPool(numberOfThreads + 1);

        for (ArrayAdditionThread worker : threadList){
            workers.execute(worker);
        }

        if (threadList.stream().filter(t -> t.getSum() == 0).count() == 0)
        workers.execute(finalCountdown);

        workers.shutdown();
    }
}
