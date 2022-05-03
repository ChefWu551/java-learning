package com.yuefeng.designModel.factoryModel.model.pizza;

public class NYCheesePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("NY Cheese Pizza prepare");
    }
}
