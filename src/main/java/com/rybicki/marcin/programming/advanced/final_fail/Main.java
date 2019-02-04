package com.rybicki.marcin.programming.advanced.final_fail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        Object lock = new Object();

        FinalFail job = new FinalFail(lock);
        FinalFail job2 = new FinalFail(lock);

        Thread one = new Thread(job);
        Thread two = new Thread(job2);

        ExecutorService workers =
                Executors.newFixedThreadPool(4);

        workers.execute(one);
        workers.execute(two);
        workers.execute(one);
        workers.execute(two);

//        lock = new Object();
        FinalFail job3 = new FinalFail(lock);
        workers.execute(job3);

        workers.shutdown();
    }
}
