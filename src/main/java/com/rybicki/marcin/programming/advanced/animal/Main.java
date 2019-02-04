package com.rybicki.marcin.programming.advanced.animal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        Random rng = new Random();

        List<Animal> animals = new ArrayList<>();

        ZooWorker jas = new ZooWorker(animals,1 + rng.nextInt(10),"Jaś");
        ZooWorker stas = new ZooWorker(animals,1 + rng.nextInt(10),"Staś");

        Stats stats = new Stats(animals,10);

        Metabolism metabolism = new Metabolism(animals,1 + rng.nextInt(5));

        Mortician morty = new Mortician(animals,7);


        animals.add(Giraffe.createGiraffeWithEverything(BigDecimal.valueOf(110.0),
                                                                        Sex.MALE,
                                                                "Reksio",
                                                       BigInteger.valueOf(100 + rng.nextInt(25)),
                                                        BigInteger.valueOf(100 + rng.nextInt(25)),
                                                        BigInteger.valueOf(12),
                                                        BigInteger.valueOf(12)));

        animals.add(Giraffe.createGiraffeWithEverything(BigDecimal.valueOf(120.0),
                Sex.FEMALE,
                "Sara",
                BigInteger.valueOf(100 + rng.nextInt(25)),
                BigInteger.valueOf(100 + rng.nextInt(25)),
                BigInteger.valueOf(14),
                BigInteger.valueOf(16)));

        animals.add(Giraffe.createGiraffeWithEverything(BigDecimal.valueOf(130.0),
                Sex.MALE,
                "Długoszyi",
                BigInteger.valueOf(100 + rng.nextInt(25)),
                BigInteger.valueOf(100 + rng.nextInt(25)),
                BigInteger.valueOf(20),
                BigInteger.valueOf(20)));

        animals.add(Giraffe.createGiraffeWithEverything(BigDecimal.valueOf(90.0),
                Sex.MALE,
                "Kark",
                BigInteger.valueOf(100 + rng.nextInt(25)),
                BigInteger.valueOf(100 + rng.nextInt(25)),
                BigInteger.valueOf(22),
                BigInteger.valueOf(22)));

        animals.add(Giraffe.createGiraffeWithEverything(BigDecimal.valueOf(115.0),
                Sex.MALE,
                "Benek",
                BigInteger.valueOf(77 + rng.nextInt(25)),
                BigInteger.valueOf(76 + rng.nextInt(25)),
                BigInteger.valueOf(14),
                BigInteger.valueOf(14)));

        animals.add(Giraffe.createGiraffeWithEverything(BigDecimal.valueOf(110.0),
                Sex.FEMALE,
                "Żaneta",
                BigInteger.valueOf(150 + rng.nextInt(25)),
                BigInteger.valueOf(120 + rng.nextInt(25)),
                BigInteger.valueOf(20),
                BigInteger.valueOf(24)));

        animals.add(Giraffe.createGiraffeWithEverything(BigDecimal.valueOf(100.0),
                Sex.MALE,
                "Burek",
                BigInteger.valueOf(88 + rng.nextInt(25)),
                BigInteger.valueOf(94 + rng.nextInt(25)),
                BigInteger.valueOf(20),
                BigInteger.valueOf(16)));

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

    }

}
