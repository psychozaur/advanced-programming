package com.rybicki.marcin.programming.advanced.notify;

public class Hop implements Runnable {

    private Object o;

    public Hop(Object o) {
        this.o = o;
    }

    @Override
    public void run() {
        while(true){
            synchronized (o){
                System.out.println("hop");
                o.notifyAll();
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
