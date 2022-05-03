package com.yuefeng.collections;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2022/5/3
 */
public class LinklistTest {

    public static void main(String[] args) {
        LinkedList<String> strings = new LinkedList<>();
        strings.add("123");
        strings.add("456");
        strings.add("789");
        // for循环遍历看get方法源码可知，当元素所在linkedList位置越靠后，其遍历的速度就会越慢
        // 因为每次get对应的index位置的元素值时候，都要把前面的元素都遍历一遍
        for (int i=0; i<strings.size(); i++) {
            System.out.println(strings.get(i));
        }

        // 使用iterator可以避免for这种情况出现
        Iterator<String> iterator = strings.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
