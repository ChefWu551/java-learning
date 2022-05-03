package com.yuefeng.proxy.aop;

import com.yuefeng.annotation.annotations.Aspect;
import com.yuefeng.annotation.annotations.SimpleInject;
import com.yuefeng.proxy.enums.InterceptPoint;
import com.yuefeng.proxy.interceptor.ServiceLogAspect;
import com.yuefeng.proxy.interceptor.SimpleInterceptor;
import lombok.SneakyThrows;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static sun.reflect.misc.MethodUtil.getMethod;


public class CGLibContainer {

    static Map<Class<?>, Map<InterceptPoint, List<Method>>> interceptMethodsMap = new HashMap<>();
    static Class<?>[] aspects = new Class<?>[] {ServiceLogAspect.class};

    static {
        init();
    }

    @SneakyThrows
    private static void init() {
        for (Class<?> cls : aspects) {
            Aspect aspect = cls.getAnnotation(Aspect.class);

            if (aspect != null) {
                Method before = getMethod(cls, "before", new Class<?>[]{Object.class, Method.class, Object[].class});
                Method after = getMethod(cls, "after", new Class<?>[]{Object.class, Method.class, Object[].class, Object.class});
                Method exception = getMethod(cls, "exception", new Class<?>[]{Object.class, Method.class, Object[].class, Throwable.class});

                Class<?>[] interceptedArr = aspect.value();
                for (Class<?> intercepted : interceptedArr ) {
                    addInterceptMethod(intercepted, InterceptPoint.BEFOR, before);
                    addInterceptMethod(intercepted, InterceptPoint.AFTER, after);
                    addInterceptMethod(intercepted, InterceptPoint.EXCEPTION, exception);
                }
            }
        }
    }

    private static void addInterceptMethod(Class<?> cls, InterceptPoint point, Method method ) {
        if (method == null) return;
        Map<InterceptPoint, List<Method>> map = interceptMethodsMap.get(cls);

        if (map == null) {
            map = new HashMap<>();
            interceptMethodsMap.put(cls, map);
        }

        List<Method> methods = map.get(point);
        if (methods == null) {
            methods = new ArrayList<>();
            map.put(point, methods);
        }

        methods.add(method);
    }

    @SneakyThrows
    public static <T> T getInstance(Class<T> cls) {
//        try{
            T obj = createInstance(cls);
            Field[] fields = obj.getClass().getDeclaredFields();

            for (Field field : fields) {
                if (!field.isAccessible()) field.setAccessible(true);

                if (field.isAnnotationPresent(SimpleInject.class)){
                    field.set(obj, getInstance(field.getType()));
                }
            }

            return obj;
//        }catch (Exception e) {
//            throw new RuntimeException();
//        }
    }

    @SneakyThrows
    private static <T> T createInstance(Class<T> cls) {

        if (!interceptMethodsMap.containsKey(cls)) return (T) cls.newInstance();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        enhancer.setCallback(new SimpleInterceptor());
        return (T) enhancer.create();
    }

}
