package com.yuefeng.jdk18.supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2022/3/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentModel {

    private String name;
    private int age;

    public static List<StudentModel> getStudents() {
        List<StudentModel> stus = new ArrayList<>();
        StudentModel stu1 = new StudentModel("张三", 20);
        stus.add(stu1);

        StudentModel stu2 = new StudentModel();
        stu2.setAge(22);
        stu2.setName("李四");
        stus.add(stu2);

        return stus;
    }

}
