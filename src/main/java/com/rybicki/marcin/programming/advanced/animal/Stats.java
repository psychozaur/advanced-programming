package com.rybicki.marcin.programming.advanced.animal;

import java.util.List;

public class Stats implements Runnable {

    //liczba wszystkich
    // żywe
    // martwe
    // sumaryczna ilość jedzenia/wody per zwierzę :D

    private final List<Animal> animals;
    private final int timeIntervalInSec;

    private long numberOfAnimalsAlive;

    private static String allAnimals;
    private static String deadAnimals;
    private static String aliveAnimals;

    private static final String messagePattern =
            "\tNumber of all animals: [%d] " + allAnimals + "\n" +
            "\t\tNumber of dead animals: [%d] " + deadAnimals + "\n" +
            "\t\tNumber of alive animals: [%d] " + aliveAnimals + "\n";

    public Stats(List<? extends Animal> animals, int timeIntervalInSec) {
        this.animals = (List<Animal>) animals;
        this.timeIntervalInSec = timeIntervalInSec;
    }

    public void listAllAnimals(){

        allAnimals += "[";
        for (Animal animal : animals){

            allAnimals += animal.getName() + " ";
        }
        allAnimals += "]";

    }

    public void listDeadAnimals(){

        deadAnimals += "[";
        for (Animal animal : animals){
            if (!animal.isAlive())
            deadAnimals += animal.getName() + " ";
        }
        deadAnimals += "]";
    }

    public void listAliveAnimals(){

        aliveAnimals += "[";
        for (Animal animal : animals){
            if (animal.isAlive())
            aliveAnimals += animal.getName() + " ";
        }
        aliveAnimals += "]";
    }

    @Override
    public void run() {

        while(true){

            try {
                Thread.sleep(Utils.convertSecToMs(timeIntervalInSec));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            numberOfAnimalsAlive = animals.stream()
                    .filter(Animal::isAlive)
                    .count();

            listAllAnimals();
            listDeadAnimals();
            listAliveAnimals();

            //wszystkie, martwe i żywe
            System.out.println(String.format(messagePattern,
                    animals.size(),
                    (animals.size() - numberOfAnimalsAlive),
                    numberOfAnimalsAlive));

            allAnimals = "";
            deadAnimals = "";
            aliveAnimals = "";
        }
    }
}
