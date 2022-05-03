package com.yuefeng.designModel.factoryModel.model.pizza;

public class CHSausagePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("CH sausage pizza prepare");
    }
}
