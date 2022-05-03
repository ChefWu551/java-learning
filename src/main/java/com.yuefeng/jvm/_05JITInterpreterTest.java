package com.yuefeng.jvm;

/**
 * JIT(just in time)：运行时将字节码翻译为机器码，从而改善字节码编译语言行能的技术，
 *      有C1（client）模式和C2模式（server，采用C++编写），
 * JIT(just in time)当某段方法代码使用频率极高，被反复调用超过一定次数以后（server默认1W次可以通过 -XX:compileThreshold，且带有半衰期，可以通过-XX:+useCountDecay来开启），
 * 会使用JIT热点代码技术，
 * 此时再次执行会变得效率很高，这也是java为什么是半解释型语言和半编译型语言的由来
 * -Xint 6490ms 使用解释器执行时间
 * -Xcomp 1809ms 使用jit即时热点代码编译器执行时间
 * -Xmixed 默认是混合模式执行时间
 *
 */
public class _05JITInterpreterTest {

    private static int times = 1000000;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        testInterpreterCount(times);
        long end = System.currentTimeMillis();

        System.out.println(start - end);
    }

    public static void testInterpreterCount(int times){
        for (int j=0; j<times; j++) {
            System.out.println(j);
        }
    }
}
