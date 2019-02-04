package com.rybicki.marcin.programming.advanced.dead_lock;

import java.util.Queue;

public class Copier implements Runnable {

    private final Queue<String> source;
    private final Queue<String> destination;

    public Copier(Queue<String> source, Queue<String> destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public void run() {
        while(true){
            synchronized (source){
                synchronized (destination){
                    if (!source.isEmpty()){
                        String data = source.poll();
                        System.out.println("Copying item to another queue: " + data);
                        destination.offer(data);
                    }
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
