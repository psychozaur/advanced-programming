package com.rybicki.marcin.programming.advanced.sync;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {

        Queue<Pizza> pizzas = new LinkedList<>();
        Lock lock = new ReentrantLock();

        Producer pizzaMaster = new Producer(pizzas,2000, lock);
        Producer pizzaMaster2 = new Producer(pizzas,2000, lock);

        Consumer pizzaAddict = new Consumer(pizzas, "Margarita", 1000, lock);
        Consumer pizzaAddict2 = new Consumer(pizzas, "Hawaii", 1000, lock);
        Consumer pizzaAddict3 = new Consumer(pizzas, "Margarita", 1000, lock);
        Consumer pizzaAddict4 = new Consumer(pizzas, "Hawaii", 1000, lock);

        Thread one = new Thread(pizzaMaster);
        Thread two = new Thread(pizzaMaster2);
        Thread three = new Thread(pizzaAddict);
        Thread four = new Thread(pizzaAddict2);
        Thread five = new Thread(pizzaAddict3);
        Thread six = new Thread(pizzaAddict4);

        ExecutorService workers =
                Executors.newFixedThreadPool(6);

        workers.execute(one);
        workers.execute(two);
        workers.execute(three);
        workers.execute(four);
        workers.execute(five);
        workers.execute(six);

    }
}
