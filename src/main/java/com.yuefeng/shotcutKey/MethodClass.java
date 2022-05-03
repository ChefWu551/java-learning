package com.yuefeng.shotcutKey;

public class MethodClass {

    @SuppressWarnings("unchecked")
    public static <T> T add(T value1, T value2) {
        if (value1 instanceof Integer && value2 instanceof Integer) {
            return  (T)String.valueOf((Integer)value1 + (Integer)value2);
        } else {
            return (T)((String)value1+(String)value2);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T sum(T t1, T t2) throws Exception {
        if (t1 instanceof Integer && t2 instanceof Integer) {
            int tmp = (Integer) t1 + (Integer) t2;
            return (T) String.valueOf(tmp);
        } else if (t1 instanceof String && t2 instanceof String) {
            return (T) ((String) t1 + (String) t2);
        } else {
            throw new Exception("Cant use sum for the type!");
        }
    }
}
