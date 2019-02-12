package com.rybicki.marcin.programming.advanced.array_addition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ArrayAdditionThread implements Runnable {

    private long[] array;
    private long[] partialArray;

    private long sum;

    private int numberOfThreads;

    private int[] distribution;
    private int startPos;
    private int ordinal;

    private Lock lock;
    private Condition condition;

    public ArrayAdditionThread(long[] array, int numberOfThreads, int ordinal, Lock lock, Condition condition) {
        this.array = array;
        this.numberOfThreads = numberOfThreads;
        this.startPos = 0;
        this.ordinal = ordinal;
        this.lock = lock;
        this.condition = condition;
        setLengthDistribution();
    }

    public long getSum() {
        return sum;
    }

    private void setLengthDistribution() {
        distribution = new int[numberOfThreads];
        int count = array.length;
        int i = 0;

            while(count > 0){

                distribution[i % numberOfThreads] += 1;

                i++;
                count--;
            }
    }

    private int calculateStartPosition(int ordinal){
        int result = 0;

        while(ordinal > 0){

            result += distribution[ordinal - 1];

            ordinal--;
        }

        return result;
    }

    public void copyArray(){
        partialArray = new long[distribution[ordinal]];

        startPos = calculateStartPosition(ordinal);

            System.arraycopy(array,startPos,partialArray,0,distribution[ordinal]);



    }

    public long performArrayAddition(long[] array, int numberOfThreads){

            for (long value : array){
                sum += value;
            }


        return sum;
    }

    @Override
    public void run() {

        lock.lock();
        try{
            condition.signalAll();

            copyArray();
            sum = performArrayAddition(this.partialArray, this.numberOfThreads);
            System.out.println(sum);

            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }





    }
}
