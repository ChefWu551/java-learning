package com.yuefeng.designModel.factoryModel.model.pizza;

public abstract class Pizza {

    public abstract void prepare();


    public void cook() {
        System.out.println("pizza is cooking");
    }

    public void cut() {
        System.out.println("pizza is cut");
    }

    public void deliver() {
        System.out.println("pizza is deliver");
    }
}
