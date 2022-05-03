package com.yuefeng.designModel.factoryModel.model;


import com.yuefeng.designModel.factoryModel.model.pizza.*;

public class PizzaFactory {

    String pizzaName;
    String location;

    public PizzaFactory(String location, String pizzaName) {
        this.pizzaName = pizzaName;
        this.location = location;
    }

    // 简单工厂：直接抽象一个方法来创建实例
    public Pizza create() {
        if (location.equals("NY")) {
            if ("sausage".equals(pizzaName)) {
                return new NYSausagePizza();
            } else {
                return new NYCheesePizza();
            }
        } else {
            if ("sausage".equals(pizzaName)) {
                return new CHSausagePizza();
            } else {
                return new CHCheesePizza();
            }
        }
    }
}
