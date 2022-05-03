package com.yuefeng.jvm;

/**
 * 常用的jvm指令
 *
 * -Xms192M -Xmx192M -XX:NewRatio=2 -XX:SurvivorRatio=6 -XX:+PrintGCDetails
 *
 */
public class _013JVMCommand {

    private static Object o1 = new Object();
    private static Object o2 = new Object();


    public static void main(String[] args) throws InterruptedException {
        Object[] objects = new Object[10000];

        for (int i=0; i<10000; i++) {
            Byte[] b = new Byte[1024 * 200];
            objects[i] = b;
            Thread.sleep(50);
        }

        // 线程死锁
        t1Run();
        t2Run();
    }

    /**
     * jps语法: 主要用于查看宏观的进程信息
     * jps [-q] [-mlvV] [<hostid>]
     * jps -h -help
     * jps 查看本地/远程java正在运行的进程id和进程名称
     * jps -q 只能看到进程的id
     * jps -l 进程id + 类的全类名 eg: 2306 com.yuefeng.jvm._013JVMCommand
     * jps -m 比直接jps信息更丰富，启动的时候传递的main新信息
     * jps -v 列出了每个线程id + 线程名称 + 启动时配置的参数信息 eg: 2349 _013JVMCommand -Xms10M -Xmx10M -XX:+UseG1GC -XX:+PrintGCDetails
     */


    /**
     * jstat语法：查看jvm统计信息，包含了类加载、内存、垃圾手机、jit编译等信息
     * jstat -<option> [-t] [-h<lines>] <vmid> [<interval> [<count>]]
     *      -option: 要查看的信息操作
     *          -class: 查看类装载的参数，如装载类的大小
     *          -gc: 查看gc的详情，各个区(eden,s0,s1,old,permanent/mataSpace) 已使用空间，总空间大小，GC的次数等
     *              使用举例： jstat -gc -t 2401(pid) 5000(interval) 10(times)
     *              若随着时间的增长ou(old use)列一直增加，则很可能会出现堆溢出
     *          -gccapacity: 同-gc，但是更侧重java堆区域各个区域使用到的最大、最小空间
     *          -gcutil: 同-gc，关注已使用的空间占总空间的占比
     *          -gccause: 与gcutil一致，但会额外输出导致最后一次或当前正在发生的gc原因
     *          -gcnew: 显示新生代的gc情况
     *          -gcnewcapacity: 显示新生代最大最小使用空间及gc情况
     *          -gcold:
     *          -gcoldcapacity:
     *          -gcpermcapacity
     *      -t: 增加时间信息，用于计算程序的总时长，可以推断出进程时间范围内的gc频率，以推断进程gc情况是否合理，是否需要增加内存
     *          借助这个-t 可以分析单位时间内，新生代，老年代，元空间(永久代)gc的频率，gc的时间，
     *          若gc时间占进程运行时间的20%，则说明目前堆的压力较大，需要考虑优化问题，
     *          如果占用时间高达90%，说明几乎没有可用的空间，随时可能会oom
     *      -h<lines>
     *
     *      vmid：
     *
     *      interval：输出的时间间隔
     *
     *      count: 输出的次数，不写默认无数次
     */

    /**
     * jinfo语法：用于查看机器参数配置信息，也可以用于修改参数配置
     *
     *
     */

    /**
     * jmap导出内存映像文件和内存使用情况
     *      主动方式：
     *          jmap -dump:[live,]format=b,file=d:/bb.hprof 16060
     *              live表示存活的对象，一般说来，要dump出来的文件都比较大，加上live是为了防止导出等待时间过长和解析文件时间过长，尽快找出线上问题并解决
     *              format=b指定生成的文件的标准格式
     *      被动方式：
     *          直接配置vm option: -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=filename.hprof
     *          为了防止进程死亡后死亡信息被清除，在oom前能主动dump出日志信息
     *      显示堆内存相关信息：
     *          jmap -heap pid file.txt 例如: jmap -heap 17520 > d:/jmap-heap.txt
     *          jmap -histo pid file.txt
     *
     * jhat：分析jmap生成的文件，jmap生成的文件也可以用jvisual vm打开
     */

    /**
     * jstack: 打印jvm线程快照，每一条线程的堆栈集合
     *      用于分析线程死锁，死循环，资源等待，请求外部资源时间过长，阻塞
     *      能够直接输出分析的原因，一般是在安全点或者安全区域执行的
     *      参数使用：
     *          -F：force 请求不被响应会强制输出
     *          -l：lock 关于锁的信息
     *          -m
     *          -h
     *      eg:
     *          jstack pid
     *          jstack -l pid
     *          jstack -l pid > jstack-death_lock.txt
     */

    /**
     * jcmd: 多功能命令行
     *  -l：list jvm process list 等同于jps -m
     *  pid help: 诺列出可以使用的参数
     *  pid 具体命令：pid help里面的命令
     */

    /**
     * gc工具分析使用：
     *      jvisualvm: 分析hprof文件
     *      eclips mat: 分析hprof文件
     *          浅堆(shallow heap)：一个对象所消耗的内存
     *          深堆(retained heap): 对象的保留集中所有对象的浅堆大小之和，所以必然>=浅堆大小
     *          保留集：若一个对象A中引用了对象B和对象C，其中对象B还被D对象引用了，C对象只有A对象引用，则此时的保留集是A和C
     *              对应的原支配树：        优化后的支配树：从优化后的支配树中可以看除，哪些对象之间有直接引用关系，哪些对象在没有食用的时候可以直接被回收
     *
     *                    B    C                        C
     *                  /  \   /                        |
     *                  D    A                  B   D   A
     *                                           \  |   /
     *                                            根节点
     */
    public static void t1Run() {
        Thread t1 = new Thread(()->{
            System.out.println("线程1开始");
            synchronized (o1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (o2) {
                    System.out.println("线程1加锁中，不会被打印");
                }
            }

        });

        t1.start();
    }

    public static void t2Run() {
        Runnable t2 = new Runnable(){
            @Override
            public void run() {
                System.out.println("线程2开始");
                synchronized (o2) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (o1) {
                        System.out.println("线程2加锁中，不会被打印");
                    }
                }

            }
        };

        Thread t22 = new Thread(t2);
        t22.start();
    }


}
