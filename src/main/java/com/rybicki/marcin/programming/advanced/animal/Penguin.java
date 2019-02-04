package com.rybicki.marcin.programming.advanced.animal;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Penguin extends Animal {

    protected Penguin(BigDecimal weight, Sex sex){
        super(weight,sex);
    }

    public static Penguin createPenguinWithWeightAndSexAndName(BigDecimal weight, Sex sex, String name) {

        Penguin result = new Penguin(weight,sex);
        result.setName(name);
        return result;
    }

    public static Penguin createPenguinWithEverything(BigDecimal weight,
                                                      Sex sex,
                                                      String name,
                                                      BigInteger waterQuantity,
                                                      BigInteger foodQuantity,
                                                      BigInteger waterUsagePerCycle,
                                                      BigInteger foodUsagePerCycle){

        Penguin result = new Penguin(weight,sex);
        result.setName(name);
        result.setWaterQuantity(waterQuantity);
        result.setFoodQuantity(foodQuantity);
        result.setWaterUsagePerCycle(waterUsagePerCycle);
        result.setFoodUsagePerCycle(foodUsagePerCycle);
        return result;
    }
}
