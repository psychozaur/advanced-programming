package com.rybicki.marcin.programming.advanced.notify;

public class Hip implements Runnable {

    private Object o;

    public Hip(Object o) {
        this.o = o;
    }

    @Override
    public void run() {
        while(true){
            synchronized (o){
                System.out.println("hip");
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
