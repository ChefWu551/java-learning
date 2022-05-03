package com.yuefeng.annotation.model;

import com.yuefeng.annotation.annotations.Format;
import com.yuefeng.annotation.annotations.Label;

import java.util.Date;

public class Student {

    @Label("姓名")
    public String name;

    @Label("生日")
    @Format(format = "YYYY/MM/DD")
    public Date birthday;

    @Format
    public Date enterDay;

    public Student(String name, Date birthday, Date enterDay) {
        this.birthday = birthday;
        this.name = name;
        this.enterDay = enterDay;
    }

}
