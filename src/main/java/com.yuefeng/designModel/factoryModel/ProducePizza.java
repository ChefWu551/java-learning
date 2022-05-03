package com.yuefeng.designModel.factoryModel;

import com.yuefeng.designModel.factoryModel.model.PizzaFactory;
import com.yuefeng.designModel.factoryModel.model.store.CHPizzaStore;
import com.yuefeng.designModel.factoryModel.model.store.NYPizzaStore;
import com.yuefeng.designModel.factoryModel.model.store.PizzaStore;

public class ProducePizza {

    public static void main(String[] args) {
        // 简单工厂，抽象方法获取对象
        PizzaFactory pf = new PizzaFactory("CH", "cheese");
        pf.create();

        // 方法工厂：定义了一个创建接口对象，使得子类可以决定实例化的对象，延缓实例化对象加载
        PizzaStore store = new NYPizzaStore();
        store.orderPizza("cheese");

        System.out.println("CHANGE LOCATION TO CH!!!!");
        PizzaStore ps = new CHPizzaStore();
        ps.orderPizza("sausage");

        // 抽象工厂：提供一个接口，用于创建相关或依赖对象的家族，而不需要明确指定具体类
    }

}
