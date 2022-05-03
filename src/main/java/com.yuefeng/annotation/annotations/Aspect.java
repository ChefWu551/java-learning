package com.yuefeng.annotation.annotations;


import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Aspect {

    Class<?>[] value();
}
