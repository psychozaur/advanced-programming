package com.rybicki.marcin.programming.advanced.dead_lock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        Queue<String> first = new LinkedList<>();
        Queue<String> second = new LinkedList<>();

        Copier copier = new Copier(first,second);
        Producer producer = new Producer(first,second,new Random());

        ExecutorService workers =
                Executors.newFixedThreadPool(2);

        workers.execute(copier);
        workers.execute(producer);

    }
}
