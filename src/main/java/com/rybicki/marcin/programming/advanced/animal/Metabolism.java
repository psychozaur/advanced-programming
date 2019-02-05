package com.rybicki.marcin.programming.advanced.animal;

import java.util.List;

public class Metabolism implements Runnable {

    private final List<Animal> animals;
    private final int timeIntervalInSec;


    public Metabolism(List<? extends Animal> animals, int timeIntervalInSec) {
        this.animals = (List<Animal>) animals;
        this.timeIntervalInSec = timeIntervalInSec;
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

            animals.forEach((Animal::consumeCalories));

//            animals.stream()
//                    .filter(Animal::isMortallyDehydratedOrStarving)
//                    .forEach(Metabolism::declareDead);

        }

    }
}
