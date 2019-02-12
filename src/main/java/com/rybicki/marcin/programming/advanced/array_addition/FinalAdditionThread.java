package com.rybicki.marcin.programming.advanced.array_addition;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class FinalAdditionThread implements Runnable {

    private Lock lock;
    private List<ArrayAdditionThread> threadList;
    private long sum;
    private Condition condition;

    public FinalAdditionThread(List<ArrayAdditionThread> threadList, Lock lock, Condition condition) {
        this.threadList = threadList;
        this.lock = lock;
        this.sum = 0;
        this.condition = condition;
    }

    public long getSum() {
        return sum;
    }

    public void sumPartialResults(){
        for (ArrayAdditionThread worker : threadList){
            sum += worker.getSum();
        }
    }

    @Override
    public void run() {

            lock.lock();
            try{
                condition.signalAll();

                sumPartialResults();
                System.out.println(sum);

            } finally {
                lock.unlock();
            }

    }
}
