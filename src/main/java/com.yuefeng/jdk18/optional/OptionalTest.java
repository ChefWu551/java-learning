package com.yuefeng.jdk18.optional;

import java.util.Optional;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2022/3/28
 */
public class OptionalTest {

    public static void main(String[] args) throws Exception {

        Person person = Person.getPerson();
        if (person.getAddress() != null) {
            if (person.getAddress().getCity() != null) {
                System.out.println(person.getAddress().getCity());
            }

            throw new Exception("person 对象是空");
        }

        // 使用optional优化
         String address = Optional.ofNullable(person)
                .map(Person::getAddress)
                .map(a->a.getCity())
                .map(value->value.substring(0, 2))
                .orElseThrow(()->new Exception("person 对象是空"));

        System.out.println(address);
    }
}
