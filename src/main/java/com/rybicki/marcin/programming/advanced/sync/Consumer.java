package com.rybicki.marcin.programming.advanced.sync;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class Consumer implements Runnable {

    private Queue<Pizza> pizzas;
    private String favoriteKind;
    private int timeIntervalInMillis;
    private final Lock lock;

    public Consumer(Queue<Pizza> pizzas, String favoriteKind, int timeIntervalInMillis, Lock lock) {
        this.pizzas = pizzas;
        this.favoriteKind = favoriteKind;
        this.timeIntervalInMillis = timeIntervalInMillis;
        this.lock = lock;
    }

    public void showPizzas(){
        pizzas.forEach(pizza -> System.out.print(pizza.getPizzaName() + ", "));
        System.out.println();
    }

    @Override
    public void run() {

        while(true){

            System.out.println("Consumer is hungry");

            try {
                Thread.sleep(timeIntervalInMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


//            if (lock.tryLock())
                lock.lock();

            if (lock.tryLock()){
                System.out.println(Thread.currentThread().getName() + " locked the opbject");
                lock.unlock();
            }else {
                System.out.println(Thread.currentThread().getName() + " DID NOT locked the object.");
            }

                if (!pizzas.isEmpty()){
                    showPizzas();
                    if (pizzas.element().getPizzaName() == favoriteKind) {
                        pizzas.poll();
                        System.out.println(favoriteKind + " omnomnomnomom");
                    }
                } else {
                    System.out.println("No pizza");
                }

//            if(lock.tryLock())
                lock.unlock();


        }


    }
}
