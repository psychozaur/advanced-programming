package com.rybicki.marcin.programming.advanced.animal;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Baboon extends Animal {

    protected Baboon(BigDecimal weight, Sex sex){
        super(weight,sex);
    }

    public static Baboon createBaboonWithWeightAndSexAndName(BigDecimal weight, Sex sex, String name) {

        Baboon result = new Baboon(weight,sex);
        result.setName(name);
        return result;
    }

    public static Baboon createBaboonWithEverything(BigDecimal weight,
                                                      Sex sex,
                                                      String name,
                                                      BigInteger waterQuantity,
                                                      BigInteger foodQuantity,
                                                      BigInteger waterUsagePerCycle,
                                                      BigInteger foodUsagePerCycle){

        Baboon result = new Baboon(weight,sex);
        result.setName(name);
        result.setWaterQuantity(waterQuantity);
        result.setFoodQuantity(foodQuantity);
        result.setWaterUsagePerCycle(waterUsagePerCycle);
        result.setFoodUsagePerCycle(foodUsagePerCycle);
        return result;
    }
}
