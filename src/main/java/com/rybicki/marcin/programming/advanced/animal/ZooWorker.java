package com.rybicki.marcin.programming.advanced.animal;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

public class ZooWorker implements Runnable {

    private List<Animal> animals;
    private int timeInSec;
    private String name;

    private static BigInteger foodIncreaseLevel = BigInteger.valueOf(8);
    private static BigInteger waterIncreaseLevel = BigInteger.valueOf(8);

    @SuppressWarnings("unchecked")
    public ZooWorker(List<? extends Animal> animals, int timeInSec, String name) {
        this.animals = (List<Animal>) Objects.requireNonNull(animals);
        this.timeInSec = timeInSec;
        this.name = name;
    }

    //dlaczego ta metoda musi być statyczna?
    private static void feedAnimal(Animal animal) {
        animal.eatAndDrink(foodIncreaseLevel, waterIncreaseLevel);
    }

    @Override
    public void run() {

        while(true){

            animals.forEach(ZooWorker::feedAnimal);

            try {
                Thread.sleep(Utils.convertSecToMs(timeInSec));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        //this odnosi się do konretnego obiektu, który musiał być instancjonowany; z feedAnimal trzeba usunąć static wtedy
//        animals.forEach(this::feedAnimal);

//        animals.forEach((animal) -> {
//            animal.eatAndDrink(foodIncreaseLevel,waterIncreaseLevel);
//                }
//        );

//        for (Animal animal : animals){
//            animal.eatAndDrink(foodIncreaseLevel,waterIncreaseLevel);
//        }
    }
}
