package com.yuefeng.designModel.factoryModel.model.pizza;

public class CHCheesePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("CH Cheese Pizza prepare");
    }
}
