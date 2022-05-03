package com.yuefeng.jdk18.optional;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2022/3/28
 */
@Data
@AllArgsConstructor
public class Person {

    private String name;
    private Address address;


    public static Person getPerson() {
        Address address = new Address("上海市");

        Person person = new Person("张三", address);

        return person;
    }
}
