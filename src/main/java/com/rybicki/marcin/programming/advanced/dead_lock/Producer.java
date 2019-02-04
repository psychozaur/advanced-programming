package com.rybicki.marcin.programming.advanced.dead_lock;

import java.util.Queue;
import java.util.Random;

public class Producer implements Runnable {

    private Queue<String> first;
    private Queue<String> second;
    private Random rng;

    public Producer(Queue<String> first, Queue<String> second, Random random) {
        this.first = first;
        this.second = second;
        this.rng = random;
    }

    @Override
    public void run() {
        while(true){
            synchronized (first){
                synchronized (second){
//                    int number = rng.nextInt(20);
//                    if (rng.nextDouble() <= 0.5){
//                        System.out.println("Added item to 1st queue: " + number);
//                        first.offer("" + number);
//                    } else {
//                        System.out.println("Added item to 2nd queue: " + number);
//                        second.offer("" + number);
//                    }
                    first.offer("" + rng.nextInt(20));
                    second.offer("" + rng.nextInt(20));
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
