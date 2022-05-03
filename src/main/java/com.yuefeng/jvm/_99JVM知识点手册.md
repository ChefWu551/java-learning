# JVM知识

## 参考资料
- 调优工具： https://docs.oracle.com/javase/8/docs/technotes/tools/windows/index.html
- 运行时参数设置：https://docs.oracle.com/javase/8/docs/technotes/tools/unix/java.html

## 整体架构图:

![JVM结构](image\JVM结构.png)

## 1. JVM运行时参数

#### 1.1. JVM参数选项类型

##### 1.1.1. 标准参数选项

```
java -h 里面包含的指令

eg: java -version // server类型编译器，64位操作系统专用，激进优化，针对栈上分配，同步消除，标量替换
```

##### 1.1.2. -X参数选项

```
java -X
	// 指定解释器的类型，int/comp/mixed，半编译半解释型语言
    -Xmixed           混合模式执行 (默认)
    -Xint             仅解释模式执行		
    // -XX:InitialHeapSize=10M
    -Xms<size>        设置初始 Java 堆大小
    // -XX:MaxHeapSize=10M
    -Xmx<size>        设置最大 Java 堆大小
    // -XX:ThreadStackSize=10M
    -Xss<size>        设置 Java 线程堆栈大小
    
    -Xbootclasspath:<用 ; 分隔的目录和 zip/jar 文件>
                      设置搜索路径以引导类和资源
    -Xbootclasspath/a:<用 ; 分隔的目录和 zip/jar 文件>
                      附加在引导类路径末尾
    -Xbootclasspath/p:<用 ; 分隔的目录和 zip/jar 文件>
                      置于引导类路径之前
    -Xdiag            显示附加诊断消息
    -Xnoclassgc       禁用类垃圾收集
    -Xincgc           启用增量垃圾收集
    -Xloggc:<file>    将 GC 状态记录在文件中 (带时间戳)
    -Xbatch           禁用后台编译
    -Xprof            输出 cpu 配置文件数据
    -Xfuture          启用最严格的检查, 预期将来的默认值
    -Xrs              减少 Java/VM 对操作系统信号的使用 (请参阅文档)
    -Xcheck:jni       对 JNI 函数执行其他检查
    -Xshare:off       不尝试使用共享类数据
    -Xshare:auto      在可能的情况下使用共享类数据 (默认)
    -Xshare:on        要求使用共享类数据, 否则将失败。
    -XshowSettings    显示所有设置并继续
    -XshowSettings:all 显示所有设置并继续
    -XshowSettings:vm 显示所有与 vm 相关的设置并继续
    -XshowSettings:properties 显示所有属性设置并继续
    -XshowSettings:locale 显示所有与区域设置相关的设置并继续
```

##### 1.1.3. -XX参数选项

以-XX开头，用于开发和调试JVM

- 分类
  - Boolean类型格式、
    - `-XX:+/-<option>` 表示启用/禁用option属性	// eg: -XX:+UseG1GC  -XX:+UseAdaptiveSizePolicy
  - Key-Value格式
    - 数值格式：`-XX：<option>=<number>` // eg: -XX:MaxHeapSize=10M -XX:NewRatio=2
    - 非数值: `-XX:<name>=<string>` // eg: -XX:HeapDumpPath=d:/auto_error_inner.hprof
- -XX:+PrintFlagsFinal
  - 输出所有参数的名称和默认值

#### 1.2. 添加jvm的运行时参数

##### 1.2.1. idea

##### 1.2.2. jar

```
java -Xms50m -XX:PrintGCDetails -jar xxx.jar
```

#### 1.3. 常用的JVM选项

##### 1.3.1. 打印设置的XX选项及值

```txt
-XX:+PrintCommandLineFlags 程序运行前打印出手动设置或者JVM自动设置的XX选项
    
-XX:+PrintFlagsInitial	表示打印出所有XX选项的默认值
    
-XX:+PrintCommandLineFlags	表示打印出XX选项运行时程序生效的值

-XX:+PrintCommandLineFlags	打印JVM的参数
```

##### 1.3.2. 堆、栈、方法区

###### a. 栈指令

```
-Xss128K // 设置栈大小位128kb
```

###### b. 堆空间

```
-Xms10M
-Xmx10M	
-Xmn100M // 设置年轻代大小
-XX:NewRatio=2 // 新生代:老年代比例 = 1：2
-XX:NewSize=100M // 设置新生代大小为100M
-XX:MaxNewSize=100M // 设置新生代最大值为100M
-XX:SurvivorRatio=8 // s0:s1:eden = 1:1:8
-XX:+UseAdaptiveSizePolicy // 自动选择各个区域大小，默认开启状态
-XX:MaxTenuringThreadshold=15 // 设置新生代s1、s0区晋升老年代的最大阈值

-XX:PreTenureSizeThreadshold=1024 设置让大于此阈值的对象直接分配在老年代，支队serial、ParNew收集器有效
```

###### c. 方法区

- 永久代

```
-XX:PermSize=256m // 设置永久代初始值
-XX:MaxPermSize=256m // 设置永久代最大值
```

- 元空间

```
-XX:MetaSpaceSize // 初始化元空间大小
-XX:MaxMetaspaceSize // 最大元空间大小

-XX:+UseCompressedOops // 压缩对象指针
-XX:+UseCompressedClassPoints // 压缩类指针
-XX:CompressedClassSpaceSize // 设置Klass Metaspace的大小，默认1G
```

- 直接内存

```
-XX:MaxDirectMemorySize // 若未指定，则默认与java堆最大值一样
```

##### 1.3.3. OutOfMemory相关选项

```
-XX:+HeapDumpOnOutOfMemoryError // oom的时候，会导出，配合 HeapDumpPath 一起使用，指定导出的文件位置

-XX:+HeapDumpBeforeFullGC	// 每次full gc前都会输出一次，配合 HeapDumpPath 一起使用，指定导出的文件位置

-XX:HeapDumpPath=<path> // 指定的导出文件路径

-XX:OnOutOfMemoryError=<path> // 指定一个可行性的程序或者脚本路径，当发生OOM的时候区执行这个脚本
eg: -XX:OnOutOfMemoryError=d:/restart.sh
```

##### 1.3.4. 垃圾回收器相关

搭配方式：

| young区               | old区           | 说明                                                         |
| --------------------- | --------------- | ------------------------------------------------------------ |
| serial GC             | serial Old GC   | 串行的垃圾收集器                                             |
| Parallel New GC       | CMS GC          | 若由于大量的内存被占用导致cms运行受阻，会采用serialOldGC这种串行的后备方案，cms在jdk14以后被移除了 |
| Parallel scanvenge GC | Parallel Old GC | jdk1.8默认                                                   |
| G1                    | G1              | jdk1.9以后默认的gc                                           |

- 查看默认的垃圾收集器

```
-XX:PrintCommandLineFlag // 在执行的时候会打印默认的垃圾回收器
```

- serial 低配，串行，新生代

```
-XX:+UseSerialGC
```

- ParNew 并行，新生代

parallel GC，配合CMS使用，

```
-XX:UseParNewGC
-XX:ParallelGCThreads=N // 并行的线程数
```

- **Parallel scanvenge**

jdk8的默认gc 年轻代的垃圾收集器

```
-XX:+UseParalleGC
-XX:+UseParalleOldGC // 老年代默认的对应的gc器，是和UseParalleGC一一对应的
-XX:ParallelGCThreads // 设置年轻代并行的手机器的线程个数，一般最好与CPU相等。默认请开给你下小于8个CPU时，线程数等于CPU数；
					  // 大于八个时，使用3+[5*CUP数量]/8
-XX:MaxGCPauseMillis	// 设置最大的stw时间，根据实际生产的需求来设置，尽可能使吞吐量和停顿时间达到一个均衡的标注，吞吐量优先
-XX:GCTimeRatio	// 垃圾手机时间占总时间的比例，用于衡量吞吐量的大小
				// 取值范围(0, 100)，默认99，也就是占总时间的1%
-XX:+UseAdaptiveSizePolicy	// 设置parallel scanvenge 收集器具有自适应调节策略
```

- CMS

并发的垃圾回收器，jdk9被标记deprecate，jdk14被干掉了

```
-XX:+UseConcMarkSweepGC
-XX:CMSInitiatingOccupanyFraction
-XX:+UseCMSCompactAtFullCollection // 用于指定在执行完Full GC后堆内存空间进行压缩整理，以此避免内存碎片的产生。
	// 简单的说gc有三种方式，标记清除，标记压缩，赋值算法，在cms一般用的是标记清除和标记压缩两种结合的方式，但是标记清除会产生碎片
	// 所以可以指定标记压缩来清除，但是这样会产生较长的停顿时间，之后分配内存空间可以通过指针碰撞的方式，这样会让晋升老年区的对象分配效率更高
-XX:CMSFullGCsBeforeCompaction // 设置在执行多少次full gc后对内存空间进行压缩整理
-XX:ParallelCMSThreads // 设置CMS的线程数量
```

- G1

由于cup、内存硬件的提升，业务的复杂化，

```
-XX:UseG1GC
-XX:G1HeapRegionSize // 设置每个region大小，值是2的幂，范围是1MB到32MB之间，目标是根据最小的java堆大小划分出约2048个区域，默认是堆内存的1/2000
-XX:MaxGCPauseMillis // 设置期望达到的最大gc停顿时间，g1会在设置的最大停顿时间里面，尽可能大的提高吞吐量，默认值是200ms
-XX:ParallelGCThread // 设置STW时GC线程数的值，最多设置为8
-XX:ConcGCThreads // 设置并发标记的线程数，推荐将n设置为并行垃圾回收线程的1/4
```

- 怎么选择垃圾回收器
  - 优先调整堆大小让jvm自适应完成
  - 如果内存小于100m，使用串行收集器
  - 如果是单核、单机程序，并且没有停顿时间要求，串行收集器
  - 如果是多cpu、需要搞吞吐量、允许停顿时间超过1秒，选择并行或者jvm自己选择
  - 如果是多cpu、追求低停顿时间，需快速响应，使用并发收集器，官方推荐g1，性能搞，现在的互联网项目几乎用的是g1

##### 1.3.5. gc日志相关

```
// 单独使用
-verbose:gc 输出gc日志信息，默认输出到标准输出
-XX:+PrintGC 等同于-verbose:gc，表示打开简化的gc日志
-XX:+PrintGCDetails 在发生垃圾回收时打印内存回收详细的日志，并在进程退出时输出当前内存各区域分配情况

// 规范日志日期格式
-XX:+PrintGCTimeStamps 输出GC发生的时间戳
-XX:+PrintGCDateStamps 输出GC发生的时间戳

// 指定存储文件位置
-Xloggc:<file> 把GC日志写入到一个文件中区，而不是打印到标准输出中

// 指定打印日志的情况
-XX:+PrintHeapAtGC 每一次GC前和GC后，都打印堆信息
```

##### 1.3.6. 其他参数

```
-XX:+DoEscapeAnalysis 开启逃逸分析
-XX:+UseTLAB 使用tlab，默认开启状态
-XX:+PrintTLAB 打印TLAB的使用情况
-XX:TLABSize 设置tlab的大小
-XX:+DisableExplicitGC 禁止使用hotspot执行system.gc(), 默认禁用
```

### 1.4. 通过java代码获取参数值

```
MemoryMXBean m = ManagementFactory.getMemoryMXBean();
MemoryUsage u = m.getHeapMemoryUsage();
u.getInit();	// 初始化堆空间大小
u.getMax();	// 最大堆空间大小
u.getUsed();	// 已经使用堆空间大小
```



## 2. 基础篇

### 2.1. jvm内部结构

### 2.2. GC及性能分析

## 3. 字节码篇

### 3.1. 字节码文件分析

## 4. 类加载器篇

### 4.1. 默认类加载器

- BootstrapClassLoader引导类加载器--rt.jar;resources.jar

- ExtClassLoader->Launcher(Launcher的子类)扩展类加载器 jre/lib/ext/*.jar

- AppClassLoader->Launcher(Launcher的子类)应用类加载器 CLASSPATH

  以上三个类加载是依次往上继承的关系，使用双亲委派机制和沙箱安全机制加载类的。

  类是按需加载的，使用以上的两个记载机制是为了，保证同一个应用中的加载的类是唯一。

  - 沙箱安全机制

    例如，自己写了一个全类名java.lang.String.java的类，在进行编译的的时候不会报错，但是在引用其类型的方法执行的时候会报错，`java.lang.SecurityException: Prohibited package name: java.lang`，因为classloader会优先使用rt.jar包下的string，防止错误的被引用自定义的类，以起到保护防止本地的类被修改的作用。

#### 4.1.1. 双亲委派机制源码

```
/**
     * Loads the class with the specified <a href="#name">binary name</a>.  The
     * default implementation of this method searches for classes in the
     * following order:
     *
     * <ol>
     *
     *   <li><p> Invoke {@link #findLoadedClass(String)} to check if the class
     *   has already been loaded.  </p></li>
     *
     *   <li><p> Invoke the {@link #loadClass(String) <tt>loadClass</tt>} method
     *   on the parent class loader.  If the parent is <tt>null</tt> the class
     *   loader built-in to the virtual machine is used, instead.  </p></li>
     *
     *   <li><p> Invoke the {@link #findClass(String)} method to find the
     *   class.  </p></li>
     *
     * </ol>
     *
     * <p> If the class was found using the above steps, and the
     * <tt>resolve</tt> flag is true, this method will then invoke the {@link
     * #resolveClass(Class)} method on the resulting <tt>Class</tt> object.
     *
     * <p> Subclasses of <tt>ClassLoader</tt> are encouraged to override {@link
     * #findClass(String)}, rather than this method.  </p>
     *
     * <p> Unless overridden, this method synchronizes on the result of
     * {@link #getClassLoadingLock <tt>getClassLoadingLock</tt>} method
     * during the entire class loading process.
     *
     * @param  name
     *         The <a href="#name">binary name</a> of the class
     *
     * @param  resolve
     *         If <tt>true</tt> then resolve the class
     *
     * @return  The resulting <tt>Class</tt> object
     *
     * @throws  ClassNotFoundException
     *          If the class could not be found
     */
public abstract class ClassLoader {
    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                try {
                    if (parent != null) {
                        // 通过迭代的方法来使用双亲委派机制
                        c = parent.loadClass(name, false);
                    } else {
                        c = findBootstrapClassOrNull(name);
                    }
                } catch (ClassNotFoundException e) {
                }

                if (c == null) {
                    long t1 = System.nanoTime();
                    c = findClass(name);

                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }
}
```

### 4.2. 自定义类加载器

#### 4.2.1. 优势

 - 可以拓展类加载器的功能例如：加密、自定义加载方式
 - 如果觉得原有的加载文件速度比较慢，可以自定义，提高类加载子系统加载文件的效率
 - 隔离应用和类之间目的，因为判断加载的类是否相等，除了要类的全类名要一致以外，还需要类加载器是同一个，这样才能保证加载到方法区中的实例对象类型Kclass是唯一的

#### 4.2.2. 实现方法

- 重写loadClass方法，不推荐，会破坏双亲委派机制
- 重写loadClass方法调用的findClass->URLClassLoader方法，保留双拼委派机制

#### 4.2.3. 加密实现

 - 通过加密算法加密编译好的class文件
 - 在加载指定类的时候，使用指定的类加载器来加载，累加器里面对二进制流文件进行了解密

#### 4.2.4. tomcat类加载器的实现

- 核心：通过重写Common类加载器加载${catalina.home}/lib/ 目录下的类库
- 随后**Catalina** 和**Shared** 继承了Common类加载器加载器加载catalina.properties的配置文件中server.loader的资源

#### 4.2.5. 类的主动引用与被动引用

​	判断是否主动引用还是被动引用，只需要看是不是会执行clinit方法（类加载时候的初始化步骤）

- 主动引用

  - ```java
    *      1.  创建类的实例，new对象
    *      2.  访问类或者接口的静态变量，或对该静态变量赋值
    *      3.  调用类的静态方法
    *      4.  通过反射操作这个类
    *      5.  初始化一个类的子类，这个类也会被跟着初始化
    *      6.  Java虚拟机启动时被标记为启动类的类
    *      7.  jdk1.7以后支持动态语言
    ```

- 被动引用

  - ```java
    *      1.  子对象引用父对象的静态字段不会导致子对象初始化
    *      2.  引用类的常量信息 eg: static final 类型的常量
    *      3.  通过数组来引用类
    ```

## 5. 性能调优篇

### 5.1. 监控性能的指标

### 5.2. 常见的命令行监控

### 5.3. 常用工具分析监控文件

### 5.4. 调优