package com.yuefeng.shotcutKey;

public class MainClass {

    /**
     * F7 碰到方法就会进入方法具体内容
     * F8 会按照当前执行步骤直接到当前步骤的下一个步骤
     * F9 只会停留在打了断点的地方
     * @param args
     */
    public  static void main(String[] args) {

        int a = 0;
        int b = 1;

        int c = MethodClass.add(a, b);

        System.out.println(c);
    }
}
