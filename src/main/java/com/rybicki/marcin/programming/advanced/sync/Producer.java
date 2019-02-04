package com.rybicki.marcin.programming.advanced.sync;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class Producer implements Runnable {

    private Queue<Pizza> pizzas;
    private Random rng;
    private int timeIntervalInMillis;
    private final Lock lock;

    public Producer(Queue<Pizza> pizzas, int timeIntervalInMillis, Lock lock) {
        this.pizzas = pizzas;
        this.timeIntervalInMillis = timeIntervalInMillis;
        this.rng = new Random();
        this.lock = lock;
    }

    @Override
    public void run() {

        while(true){

                System.out.println("Chef is busy");

                try {
                    Thread.sleep(timeIntervalInMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



//            if(lock.tryLock())
                lock.lock();

            if (lock.tryLock()){
                System.out.println(Thread.currentThread().getName() + " locked the opbject");
                lock.unlock();
            }else {
                System.out.println(Thread.currentThread().getName() + " DID NOT locked the object.");
            }

                if (rng.nextDouble() < 0.5) {
                    pizzas.offer(new Pizza("Margarita"));
                    System.out.println("Margarita done!");
                } else {
                    pizzas.offer(new Pizza("Hawaii"));
                    System.out.println("Hawaii done!");
                }

//                if(lock.tryLock())
                    lock.unlock();

        }


    }
}
