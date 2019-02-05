package com.rybicki.marcin.programming.advanced.animal;

import java.util.List;
import java.util.concurrent.locks.Lock;

public class Mortician implements Runnable {

    private Lock lock;
    private final List<Animal> animals;
    private final int timeIntervalInSec;

    public Mortician(List<? extends Animal> animals, int timeIntervalInSec, Lock lock) {
        this.animals = (List<Animal>) animals;
        this.timeIntervalInSec = timeIntervalInSec;
        this.lock = lock;
    }

    @Override
    public void run() {

        while (animals.size() > 0) {

            try {
                Thread.sleep(Utils.convertSecToMs(timeIntervalInSec));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.lock();

            animals.removeIf(animal -> !animal.isAlive());

            lock.unlock();
        }
    }
}
