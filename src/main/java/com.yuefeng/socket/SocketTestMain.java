package com.yuefeng.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketTestMain {

    public static void main(String[] args) {
        Socket socket = null;
//        String host = "time.nist.gov";
//        int port = 13;

        String host = "www.baidu.com";
        int port = 80;
        try {
            socket = new Socket(host, port);
            socket.setSoTimeout(15000);
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.US_ASCII);
            StringBuilder time = new StringBuilder();
            for (int i = isr.read(); i != -1; i = isr.read()){
                time.append((char)i);
            }

            System.out.println(time);
            System.out.println("123");
        }catch (IOException e) {

        }
    }
}
