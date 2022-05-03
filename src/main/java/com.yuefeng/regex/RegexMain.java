package com.yuefeng.regex;

public class RegexMain {

    public static void main(String[] args) {
//        String postCodeRaw = "789456我爱33333333336789中123.华123456";
//        RegexUtil.printPostCode(postCodeRaw);

        String telephoneRaw = "+8618270880727爱我13959807551中8677777777777华+86 18270880727aaa8718270883512";
        RegexUtil.printTelephone(telephoneRaw);
    }
}
