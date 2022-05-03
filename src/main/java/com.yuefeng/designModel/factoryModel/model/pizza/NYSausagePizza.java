package com.yuefeng.designModel.factoryModel.model.pizza;

public class NYSausagePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("NY sausage pizza prepare");
    }
}
