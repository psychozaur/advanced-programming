package com.rybicki.marcin.programming.advanced.animal;

import java.util.List;
import java.util.concurrent.locks.Lock;

public class Metabolism implements Runnable {

    private Lock lock;
    private final List<Animal> animals;
    private final int timeIntervalInSec;


    public Metabolism(List<? extends Animal> animals, int timeIntervalInSec, Lock lock) {
        this.animals = (List<Animal>) animals;
        this.timeIntervalInSec = timeIntervalInSec;
        this.lock = lock;
    }

//    private static void declareDead(Animal animal) {
//        animal.setAlive(false);
//    }

    @Override
    public void run() {

        while (animals.size() > 0){

            try {
                Thread.sleep(Utils.convertSecToMs(timeIntervalInSec));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.lock();

            animals.forEach((Animal::consumeCalories));

            lock.unlock();

//            animals.stream()
//                    .filter(Animal::isMortallyDehydratedOrStarving)
//                    .forEach(Metabolism::declareDead);

        }

    }
}
