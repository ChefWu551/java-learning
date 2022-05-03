package com.yuefeng.designModel.factoryModel.model.store;

import com.yuefeng.designModel.factoryModel.model.pizza.*;

public class CHPizzaStore extends PizzaStore {


    @Override
    protected Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new CHCheesePizza();
        } else {
            return new CHSausagePizza();
        }
    }
}
