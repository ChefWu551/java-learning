package com.yuefeng.jvm;

public class _01JavaPTest {

    private String strConstant;

    private static int aaa = 10;

    public static void main(String[] args) {
        int b = 10000000;
        byte a = 1;
        int c = a + b + aaa;
        final int d = 1;
        final int f = 1122;
        System.out.println(c);
        _01JavaPTest pTest = new _01JavaPTest();
        pTest.printValue();
    }


    /**
     * 知识点1：
     *      根据字节码反编译的文件可以知道：
     *      byte/short/boolean/char/int/float:在局部变量表中占用的槽位（slot）是1
     *      long/double:在局部变量表中占用的槽位（slot）是2，可以根据局部变量表的索引序号(index)来查看
     *
     * 知识点2：
     *      printValue()方法所对应的，局部变量表存放的有四个变量，
     *      分别是，this(非静态方法特有的)、k、m、n
     *
     *  知识点3：
     *  查看code部分：
     *      bipush表示：byte to int push，官方Push byte，低于int类型的数据类型均会转换为int进行计算的
     *      sipush表示：short to int push
     *      ldc表示：当前item放入运行常量池，Push item from run-time constant pool
     *      若返回为void则：return，若有非void返回则：areturn，表示有返回引用
     *
     * @return
     */
    public Double printValue() {
        int a;
        short k = 10;
        long m = 1000;
        double n = k + m;
        return n;
    }

    /**
     *
     * 局部变量是需要初始化的，否则无法使用，而类变量则是不需要的
     * 因为类变量在类加载系统的linking阶段的prepare阶段就会初始化好，使用类型对应的初始化值，
     * 而局部变量则不会，因为在类加载的时候是不会初始化的
     *
     */
    public void printString() {
        // 编译出错
        String a;
//        System.out.println(a);
        System.out.println(strConstant);
    }
}
