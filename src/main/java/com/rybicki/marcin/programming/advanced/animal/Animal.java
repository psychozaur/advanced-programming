package com.rybicki.marcin.programming.advanced.animal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;

public abstract class Animal {

    protected BigInteger waterQuantity;
    protected BigInteger foodQuantity;

    protected boolean isAlive;
    protected BigDecimal weight;
    protected Sex sex;

    protected BigInteger waterUsagePerCycle;
    protected BigInteger foodUsagePerCycle;

    protected String name;

    protected Animal(BigDecimal weight, Sex sex){
        isAlive = true;
        this.weight = Objects.requireNonNull(weight, "Weight must be non-null!");
        this.sex = Objects.requireNonNull(sex, "Sex must be non-null!");
    }

    public void eatAndDrink(BigInteger food, BigInteger water){

        if (isAlive){
            this.waterQuantity = this.waterQuantity.add(water);
            this.foodQuantity = this.foodQuantity.add(food);
        }

    }

    public void consumeCalories() {

        if (isAlive){
            this.waterQuantity = this.waterQuantity.subtract(waterUsagePerCycle);
            this.foodQuantity = this.foodQuantity.subtract(foodUsagePerCycle);
        }

        if (getFoodQuantity().intValue() <= 0 ||
            getWaterQuantity().intValue() <= 0){
            this.setAlive(false);
        }
    }

    @Override
    public String toString() {
        return "Animal{" +
                "waterQuantity=" + waterQuantity +
                ", foodQuantity=" + foodQuantity +
                ", isAlive=" + isAlive +
                ", weight=" + weight +
                ", sex=" + sex +
                ", waterUsagePerCycle=" + waterUsagePerCycle +
                ", foodUsagePerCycle=" + foodUsagePerCycle +
                ", name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = validateParameter(name,"Name has to be non-null!");
    }

    public void setWaterQuantity(BigInteger waterQuantity) {
        this.waterQuantity = validateParameter(waterQuantity,"Water quantity has to be non-null!");
    }

    public void setFoodQuantity(BigInteger foodQuantity) {
        this.foodQuantity = validateParameter(foodQuantity,"Food quantity has to be non-null!");
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = validateParameter(weight,"Water quantity has to be non-null!");
    }

    public void setWaterUsagePerCycle(BigInteger waterUsagePerCycle) {
        this.waterUsagePerCycle = validateParameter(waterUsagePerCycle,"Water usage per cycle has to be non-null!");
    }

    public void setFoodUsagePerCycle(BigInteger foodUsagePerCycle) {
        this.foodUsagePerCycle = validateParameter(foodUsagePerCycle,"Food usage per cycle has to be non-null!");
    }

    protected <T> T validateParameter(T param, String failMessage){
        if (null == param){
            throw new NullPointerException(failMessage);
        }
        return param;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public BigInteger getWaterQuantity() {
        return waterQuantity;
    }

    public BigInteger getFoodQuantity() {
        return foodQuantity;
    }

    public boolean isAlive() {
        return isAlive;
    }

//    public boolean isMortallyDehydratedOrStarving() {
//        if (getWaterQuantity().compareTo(BigInteger.valueOf(0)) == -1 ||
//                getWaterQuantity().compareTo(BigInteger.valueOf(0)) == 0 ||
//                getFoodQuantity().compareTo(BigInteger.valueOf(0)) == -1 ||
//                getFoodQuantity().compareTo(BigInteger.valueOf(0)) == 0){
//            return true;
//        }
//        return false;
//    }

    public BigDecimal getWeight() {
        return weight;
    }

    public Sex getSex() {
        return sex;
    }

    public BigInteger getWaterUsagePerCycle() {
        return waterUsagePerCycle;
    }

    public BigInteger getFoodUsagePerCycle() {
        return foodUsagePerCycle;
    }
}
