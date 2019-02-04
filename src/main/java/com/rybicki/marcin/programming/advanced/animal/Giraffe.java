package com.rybicki.marcin.programming.advanced.animal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public class Giraffe extends Animal {

    protected Giraffe(BigDecimal weight, Sex sex){
        super(weight,sex);
    }

    public static Giraffe createGiraffeWithWeightAndSexAndName(BigDecimal weight, Sex sex, String name) {

        Giraffe result = new Giraffe(weight,sex);
        result.setName(name);
        return result;
    }

    public static Giraffe createGiraffeWithEverything(BigDecimal weight,
                                                      Sex sex,
                                                      String name,
                                                      BigInteger waterQuantity,
                                                      BigInteger foodQuantity,
                                                      BigInteger waterUsagePerCycle,
                                                      BigInteger foodUsagePerCycle){

        Giraffe result = new Giraffe(weight,sex);
        result.setName(name);
        result.setWaterQuantity(waterQuantity);
        result.setFoodQuantity(foodQuantity);
        result.setWaterUsagePerCycle(waterUsagePerCycle);
        result.setFoodUsagePerCycle(foodUsagePerCycle);
        return result;
    }
}
