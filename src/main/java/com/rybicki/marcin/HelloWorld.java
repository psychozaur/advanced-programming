package com.rybicki.marcin;

public class HelloWorld {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("Hello world");
        runnable.run();
    }
}
