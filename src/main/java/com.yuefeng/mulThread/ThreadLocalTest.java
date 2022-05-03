package com.yuefeng.mulThread;

import java.net.HttpCookie;
import java.text.SimpleDateFormat;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2022/4/8
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal<String> values = new ThreadLocal<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("");
    }
}
