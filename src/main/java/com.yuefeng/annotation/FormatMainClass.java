package com.yuefeng.annotation;

import com.yuefeng.annotation.annotations.Format;
import com.yuefeng.annotation.annotations.Label;
import com.yuefeng.annotation.model.Student;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatMainClass {

    public static void main(String[] args) throws ParseException {
        FormatMainClass formatMainClass = new FormatMainClass();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
        Student student = new Student("wu", sdf.parse("1994-08-08"), sdf.parse("2000-01-01"));
        System.out.println(formatMainClass.annotationFormat(student));
    }

    @SneakyThrows
    private <T> String annotationFormat(T t) {
        StringBuilder result = new StringBuilder();

        Field[] fields = t.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (!field.isAccessible()) field.setAccessible(true);

            Label label = field.getAnnotation(Label.class);
            String labelName = label == null ? field.getName() : label.value();
            Object value = field.get(t);
            if (field.getType().equals(Date.class)) {
                Format format = field.getAnnotation(Format.class);
                if (format != null) {
                    String pattern =  format.format() ;
                    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                    value = sdf.format(field.get(t));
                }
            }

            result.append(labelName).append(": ").append(value).append("\n");
        }

        return result.toString();
    }
}
