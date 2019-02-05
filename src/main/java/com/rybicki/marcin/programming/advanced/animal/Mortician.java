package com.rybicki.marcin.programming.advanced.animal;

import java.util.List;

public class Mortician implements Runnable {

    private final List<Animal> animals;
    private final int timeIntervalInSec;

    public Mortician(List<? extends Animal> animals, int timeIntervalInSec) {
        this.animals = (List<Animal>) animals;
        this.timeIntervalInSec = timeIntervalInSec;
    }

    @Override
    public void run() {

        while (animals.size() > 0) {

            try {
                Thread.sleep(Utils.convertSecToMs(timeIntervalInSec));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            animals.removeIf(animal -> !animal.isAlive());

        }
    }
}
