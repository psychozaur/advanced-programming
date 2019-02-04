package com.rybicki.marcin.programming.advanced.singleton;

// lazy
public class Secret {

    private static Secret INSTANCE;

    private Secret(){

    }

    public static void hahalol(){
        //każdy wątek może ją wykonać; synchronized ma tu sens?
    }

    public static Secret getInstance(){
        if(null == INSTANCE) {
            synchronized (Secret.class){
                if(null == INSTANCE)
                    INSTANCE = new Secret();
            }
        }
        return INSTANCE;
    }
}
