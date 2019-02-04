package com.rybicki.marcin.programming.advanced.animal;

public class Utils {

    private Utils(){
        throw new UnsupportedOperationException("Never instantiate Utils");
    }

    public static long convertSecToMs(long timeIntervalInSec) {
        return timeIntervalInSec * 1000;
    }
}
