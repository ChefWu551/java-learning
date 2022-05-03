package com.yuefeng.effectiveJava;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaTest {

    public static void main(String[] args) {

    }


    public List<String> sort(List<String> strings) {
        Collections.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });

        Collections.sort(strings, ((o1, o2) -> o1.compareToIgnoreCase(o2)));

        Collections.sort(strings, (String::compareToIgnoreCase));

        strings.sort((String::compareToIgnoreCase));

        return strings;
    }

}
