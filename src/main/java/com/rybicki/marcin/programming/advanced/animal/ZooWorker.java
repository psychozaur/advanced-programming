package com.rybicki.marcin.programming.advanced.animal;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Lock;

public class ZooWorker implements Runnable {

    private Lock lock;
    private List<Animal> animals;
    private int timeInSec;
    private String name;

    private static BigInteger foodIncreaseLevel = BigInteger.valueOf(8);
    private static BigInteger waterIncreaseLevel = BigInteger.valueOf(8);

    @SuppressWarnings("unchecked")
    public ZooWorker(List<? extends Animal> animals, int timeInSec, String name, Lock lock) {
        this.animals = (List<Animal>) Objects.requireNonNull(animals);
        this.timeInSec = timeInSec;
        this.name = name;
        this.lock = lock;
    }

    //dlaczego ta metoda powinna być statyczna?
    private static void feedAnimal(Animal animal) {
        animal.eatAndDrink(foodIncreaseLevel, waterIncreaseLevel);
    }

    @Override
    public void run() {

        while(animals.size() > 0){

            lock.lock();

            animals.forEach(ZooWorker::feedAnimal);

            lock.unlock();

            try {
                Thread.sleep(Utils.convertSecToMs(timeInSec));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        //this odnosi się do konretnego obiektu, który musiał być instancjonowany; z feedAnimal trzeba usunąć static wtedy
//        animals.forEach(this::feedAnimal);

    }
}
