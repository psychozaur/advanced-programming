package com.rybicki.marcin.programming.advanced.singleton;

public class Main {

    public static void main(String[] args) {

        Secret secret = Secret.getInstance();

        Secret secret2 = Secret.getInstance();




    }
}
