package com.yuefeng.designModel.factoryModel.model.store;

import com.yuefeng.designModel.factoryModel.model.pizza.NYCheesePizza;
import com.yuefeng.designModel.factoryModel.model.pizza.NYSausagePizza;
import com.yuefeng.designModel.factoryModel.model.pizza.Pizza;

public class NYPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new  NYCheesePizza();
        } else {
            return new NYSausagePizza();
        }
    }
}
