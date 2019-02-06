package com.rybicki.marcin.programming.advanced.animal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static List<Animal> getPopulatedAnimalList(Random rng){
        List<Animal> animals = new ArrayList<>();

        List<String> animalNames = Arrays.asList("Stefan","Żaneta",
                "Melvin","Kobyła",
                "Shrek","Fiona",
                "Benek","Sara");

        for (int i = 0; i < animalNames.size(); i++){
            animals.add(Giraffe.createGiraffeWithEverything(BigDecimal.valueOf(90.0 + 10.0*i),
                                                            i % 2 == 0 ? Sex.MALE : Sex.FEMALE,
                                                            animalNames.get(i),
                                                            BigInteger.valueOf(90 + 10*i + rng.nextInt(25)),
                                                            BigInteger.valueOf(90 + 10*i + rng.nextInt(25)),
                                                                    BigInteger.valueOf(10 + 2*i + rng.nextInt(5)),
                                                                    BigInteger.valueOf(10 + 2*i + rng.nextInt(5))));
        }

        return animals;
    }

    public static void main(String[] args) {

        Random rng = new Random();
        Lock lock = new ReentrantLock();
        Condition isBusy = lock.newCondition();
        List<Animal> animals = getPopulatedAnimalList(rng);

        ZooWorker jas = new ZooWorker(animals,10 + rng.nextInt(10),"Jaś",lock,isBusy);
        ZooWorker stas = new ZooWorker(animals,10 + rng.nextInt(10),"Staś",lock,isBusy);

        Stats stats = new Stats(animals,5,lock,isBusy);

        Metabolism metabolism = new Metabolism(animals,1 + rng.nextInt(5),lock,isBusy);

        Mortician morty = new Mortician(animals,7,lock,isBusy);

        // 1st way
        Thread one = new Thread(metabolism);
        Thread two = new Thread(stats);
        Thread three = new Thread(jas);
        Thread four = new Thread(stas);
        Thread five = new Thread(morty);

//        one.start();
//        two.start();
//        three.start();
//        four.start();
//        five.start();

        //2nd way
        ExecutorService workers =
                Executors.newFixedThreadPool(5);
        workers.execute(one);
        workers.execute(two);
        workers.execute(three);
        workers.execute(four);
        workers.execute(five);

        workers.shutdown();

    }

}
