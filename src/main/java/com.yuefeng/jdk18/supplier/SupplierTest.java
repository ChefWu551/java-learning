package com.yuefeng.jdk18.supplier;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2022/3/26
 *
 * 重要：这里的supplier对象在get之前其实只是一个包装类，并没有真正的运行：
 * Supplier<List<StudentModel>> students = () -> arrayStu.stream().filter(e->e.getAge() > 21).collect(Collectors.toList());
 * 这里的students在调用get()方法之前并没有真正的filter，在调用get方法的时候才执行的filter操作；
 */
public class SupplierTest {

    public static void main(String[] args) {
        // 返回一个supplier对象，入参自定义，出参自定义，根据实际的函数式方法来
        Supplier<Integer> supplier = () -> new Random().nextInt();
        System.out.println(supplier);

        List<StudentModel> arrayStu = StudentModel.getStudents();
        List<StudentModel> students1 = arrayStu.stream().filter(e->e.getAge() > 21).collect(Collectors.toList());
        Supplier<List<StudentModel>> students = () -> arrayStu.stream().filter(e->e.getAge() > 21).collect(Collectors.toList());

        for(StudentModel student : students.get()) {
            System.out.println(student.toString());
        }

    }
}
