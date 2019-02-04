package com.rybicki.marcin.programming.advanced.notify;

public class Rap implements Runnable {

    private Object o;

    public Rap(Object o) {
        this.o = o;
    }

    @Override
    public void run() {
        while(true){
            synchronized (o){
                System.out.println("rap");
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
