package com.rybicki.marcin.programming.advanced.animal;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stats implements Runnable {

    //liczba wszystkich
    // żywe
    // martwe
    // sumaryczna ilość jedzenia/wody per zwierzę :D

    private Lock lock;
    private final List<Animal> animals;
    private final int timeIntervalInSec;
    private final Condition isBusy;

    private long numberOfAnimalsAlive;

    private static final String messagePattern =
            "\tNumber of all animals: [%d] [%s] \n" +
            "\t\tNumber of dead animals: [%d] [%s] \n" +
            "\t\tNumber of alive animals: [%d] [%s] \n";

    public Stats(List<? extends Animal> animals, int timeIntervalInSec, Lock lock, Condition isBusy) {
        this.animals = (List<Animal>) animals;
        this.timeIntervalInSec = timeIntervalInSec;
        this.lock = lock;
        this.isBusy = isBusy;
    }

    public String listAnimals(Predicate<Animal> predicate){

        return animals
                .stream()
                .filter(predicate)
                .map(Animal::getName)
                .flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())
                .collect(Collectors.joining(", "));

    }


    @Override
    public void run() {

        while(animals.size() > 0){

//            try {
//                Thread.sleep(Utils.convertSecToMs(timeIntervalInSec));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            //czyszczenie konsoli na Linuxie? może nie być widoczne w IntelliJ czy Eclipse...
            try {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }

            lock.lock();

            try {
                isBusy.await(timeIntervalInSec, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            numberOfAnimalsAlive = animals.stream()
                    .filter(Animal::isAlive)
                    .count();

            //wszystkie, martwe i żywe
            System.out.println(String.format(messagePattern,
                    animals.size(), listAnimals(x -> true),
                    (animals.size() - numberOfAnimalsAlive), listAnimals(animal -> !animal.isAlive()),
                    numberOfAnimalsAlive, listAnimals(Animal::isAlive)));

            isBusy.signal();

            lock.unlock();

        }
    }
}
