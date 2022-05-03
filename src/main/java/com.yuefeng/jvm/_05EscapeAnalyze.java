package com.yuefeng.jvm;

/**
 * 逃逸分析：并不是直接的优化手段，而是一个代码分析，为其他优化手段提供栈上分配、标量替换、同步消除提供依据
 *      a. 同步消除：如果确定一个对象不会逃逸出线程，无法被其他的线程访问到，那该对象的读写就不会存在竞争，则可以消除该对象的同步锁
 *      b. 标量替换：经过逃逸分析后发现，一个聚合量在没有被外部引用的情况下，可以拆成两个变量替换，
 *          例如：Pint(x, y) p = new P(10, 5); System.out.pring(p.x + p.y)
 *          分析后可替换成：x = 10; y = 5; System.out.print(x + y);\
 *      c. 栈上分配：对象一般会存储在堆中，但是经过逃逸分析后，有些对象可以直接存储在栈上（其实原理还是标量替换），可以生成100W个对象来观察堆上面是否真的有100W个对象
 *  判断对象是否发生了逃逸原则：
 *      a. 静态变量，外部线程可见，会逃逸
 *      b. 赋值给堆中实例字段，外部线程可见，发生逃逸
 *      c. 返回实例，外部线程可见，发生逃逸
 *      d. 仅线程可见，对象无逃逸-方法体内为实例对象且无返回值，或者返回值为对象的变体，例如object.toString(),
 */
public class _05EscapeAnalyze {

    public static Object gloabalObject;

    public void globalEscape() {
        gloabalObject = new Object(); // 静态变量，外部线程共用（存储在方法区中），发生了逃逸分析
    }

    public Object returnEscape() {
        return new Object(); // 发生了逃逸分析，赋值给堆中实例字段，外部线程可见，发生逃逸
    }

    public String noEscape() {
        // 同步消除，因为线程安全，所以不会发生逃逸，不用加锁，执行效率更高
        synchronized (new Object()) {

        }

        // 线程安全，因为局部实例对象线程安全，且无返回值，所以不会发生逃逸，栈上分配对象，只有入栈出栈操作，不用堆内gc，效率更高
        Object o = new Object();

        // 返回的是对象的变体，而不是对象本身，所以不会发生逃逸
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.toString() ;

    }
}
