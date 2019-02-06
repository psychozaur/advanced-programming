package com.rybicki.marcin.programming.advanced.animal;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Metabolism implements Runnable {

    private Lock lock;
    private final List<Animal> animals;
    private final int timeIntervalInSec;
    private final Condition isBusy;


    public Metabolism(List<? extends Animal> animals, int timeIntervalInSec, Lock lock, Condition isBusy) {
        this.animals = (List<Animal>) animals;
        this.timeIntervalInSec = timeIntervalInSec;
        this.lock = lock;
        this.isBusy = isBusy;
    }


    @Override
    public void run() {

        while (animals.size() > 0){

//            try {
//                Thread.sleep(Utils.convertSecToMs(timeIntervalInSec));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            lock.lock();

            try {
                isBusy.await(timeIntervalInSec,TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            animals.forEach((Animal::consumeCalories));

            isBusy.signal();

            lock.unlock();

        }

    }
}
