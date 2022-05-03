package com.yuefeng.jdk18.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2022/3/26
 *
 * ！！！我们甚至可以自定义一个对象，并且返回类型通过声明方式来传入，入参类型也通过声明方式来传入
 * 此时就可以达到，入参和出参全部是自定义的效果，并且还可以有函数式编程能力，这种设计方式及其灵活
 */

public class PredicateTest {

    public static void main(String[] args) {
        // 这里使用了函数式编程，直接替换了Predicate 类里面的抽象方法：  boolean test(T t);
        // 返回一个自定的boolean对象
        Predicate<Integer> predicate = x -> x >5;

        List<Integer> list = new ArrayList<Integer>(){{add(5);add(6);add(7);}};

        // 直接使用predict对象进行过滤
        List<Integer> elementValueGt6 = list.stream().filter(predicate).collect(Collectors.toList());
        list.stream().filter(x->x>5).collect(Collectors.toList()).forEach(System.out::println);
    }

    // predict的用法场景
    // 场景一： 替代丑陋返回值是布尔类型的繁琐
    public void replaceBoolean() {
        String str = "aaaabbb";

        // 当然这里也可以写成：System.out.println(str.length() > 5);
        // 但是这里的是list<object> 的时候你就没办法这么搞了，使用predict+函数式编程可以直接操作，语法优美
        if (str.length() > 5) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }

        Predicate<String> result = (s) -> s.length() > 5;
        System.out.println(result);
    }
}
