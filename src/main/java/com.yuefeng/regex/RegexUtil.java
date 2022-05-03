package com.yuefeng.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    private static final String POST_CODE_REG = "(?<![0-9])[0-9]{6}(?![0-9])";

    private static final String TELEPHONE_REG = "(?<![0-9])(0|\\+86|86)?\\s?1[0-9]{10}(?![0-9])";

    public static void printPostCode(String rawStr) {
        Pattern pattern = Pattern.compile(POST_CODE_REG);
        Matcher matcher = pattern.matcher(rawStr);
        while (matcher.find()) {
            System.out.println("邮编是：" + matcher.group());
        }
    }

    public static void printTelephone(String rawStr) {
        Pattern pattern = Pattern.compile(TELEPHONE_REG);
        Matcher matcher = pattern.matcher(rawStr);
        while (matcher.find()) {
            System.out.println("电话是：" + matcher.group());
        }
    }
}
