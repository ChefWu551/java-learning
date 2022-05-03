package com.yuefeng.excetionReturn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainResource {

    public void tryWithResource() throws Exception {
        try (AutoCloseable r = new FileInputStream("hello")) {

        }
    }

    public void tryCatch() {
        try {
            AutoCloseable r = new FileInputStream("aa");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}