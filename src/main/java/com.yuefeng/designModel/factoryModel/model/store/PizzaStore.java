package com.yuefeng.designModel.factoryModel.model.store;

import com.yuefeng.designModel.factoryModel.model.pizza.Pizza;

public abstract class PizzaStore {

    public void orderPizza(String type) {
        Pizza pizza;
        pizza = createPizza(type);
        pizza.prepare();
        pizza.cook();
        pizza.cut();
        pizza.deliver();
    }

    // 方法工厂：定义了一个创建接口对象，使得子类可以决定实例化的对象，延缓实例化对象加载
    protected abstract Pizza createPizza(String type);
}
