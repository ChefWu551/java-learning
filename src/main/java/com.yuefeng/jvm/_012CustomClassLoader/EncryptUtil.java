package com.yuefeng.jvm._012CustomClassLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 加密类
 *
 * @author Jason
 *
 */
public class EncryptUtil {
    /**
     * 将数据从源文件中读取出来，让其每一位数据都取异或1的值，再写入目标文件
     *
     * @param src
     *            源文件
     * @param des
     *            加密后的文件
     * @throws Exception
     */
    public static void encrypt(File src, File des) throws Exception {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(des);

        int ch;
        while (-1 != (ch = in.read())) {
            ch = ch ^ 0xff;//加密，0变成1，1变成0
            out.write(ch);
        }
        in.close();
        out.close();
    }
}
