package com.rybicki.marcin.programming.advanced.animal;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ZooWorker implements Runnable {

    private Lock lock;
    private List<Animal> animals;
    private int timeInSec;
    private String name;
    private final Condition isBusy;


    private static BigInteger foodIncreaseLevel = BigInteger.valueOf(8);
    private static BigInteger waterIncreaseLevel = BigInteger.valueOf(8);

    @SuppressWarnings("unchecked")
    public ZooWorker(List<? extends Animal> animals, int timeInSec, String name, Lock lock, Condition isBusy) {
        this.animals = (List<Animal>) Objects.requireNonNull(animals);
        this.timeInSec = timeInSec;
        this.name = name;
        this.lock = lock;
        this.isBusy = isBusy;
    }

    //dlaczego ta metoda powinna być statyczna?
    private static void feedAnimal(Animal animal) {
        animal.eatAndDrink(foodIncreaseLevel, waterIncreaseLevel);
    }

    @Override
    public void run() {

        while(animals.size() > 0){

            lock.lock();

            try {
                isBusy.await(timeInSec, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            animals.forEach(ZooWorker::feedAnimal);

            isBusy.signal();

            lock.unlock();


//            try {
//                Thread.sleep(Utils.convertSecToMs(timeInSec));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        }

        //this odnosi się do konretnego obiektu, który musiał być instancjonowany; z feedAnimal trzeba usunąć static wtedy
//        animals.forEach(this::feedAnimal);

    }
}
