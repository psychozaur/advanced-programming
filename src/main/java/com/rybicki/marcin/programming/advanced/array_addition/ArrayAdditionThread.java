package com.rybicki.marcin.programming.advanced.array_addition;

import java.util.concurrent.locks.Lock;

public class ArrayAdditionThread implements Runnable {

    private long[] array;
    private long[] partialArray;

    public long sum;

    private int numberOfThreads;

    private int[] distribution;
    private int startPos;
    private int ordinal;

    private Object lock;

    public ArrayAdditionThread(long[] array, int numberOfThreads, int ordinal, Object lock) {
        this.array = array;
        this.numberOfThreads = numberOfThreads;
        this.startPos = 0;
        this.ordinal = ordinal;
        this.lock = lock;
        setLengthDistribution();
    }

    private void setLengthDistribution() {
        distribution = new int[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++){
            if (i == 0){
                distribution[i] = (array.length / numberOfThreads) + (array.length % numberOfThreads);
            } else {
                distribution[i] = array.length / numberOfThreads;
            }
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

        synchronized (lock){

            System.arraycopy(array,startPos,partialArray,0,distribution[ordinal]);

            lock.notify();

            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public long performArrayAddition(long[] array, int numberOfThreads){
        long result = 0;

        synchronized (lock){

            for (long value : array){
                result += value;
            }

            lock.notify();

            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public void run() {
            copyArray();
            sum = performArrayAddition(this.partialArray, this.numberOfThreads);
            System.out.println(sum);
    }
}
