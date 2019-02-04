package com.rybicki.marcin.programming.advanced.notify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotifyExample {

    public static void main(String[] args) {

        Object o = new Object();


        Hip hip = new Hip(o);
        Hop hop = new Hop(o);
//        Rap rap = new Rap(o);

        ExecutorService workers =
                Executors.newFixedThreadPool(3);

        workers.execute(hip);
        workers.execute(hop);
//        workers.execute(rap);

//        try {
//                System.out.println("Before");
//                o.wait();
//            System.out.println("After");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


    }
}
