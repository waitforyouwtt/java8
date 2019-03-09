package com.yidiandian.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @Author: 一点点
 * @Date: 2019/3/2 18:39
 * @Version 1.0
 */
public class StreamFilter {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 6, 7, 7, 1);
        List<Integer> result = list.stream().filter(i -> i % 2 == 0).collect(toList());
        System.out.println("得到的结果："+result);
        List<Integer> distinct =list.stream().distinct().collect(toList());
        System.out.println("去重："+distinct);
        result = list.stream().skip(5).collect(toList());
        System.out.println("跳过前面的5个元素："+result);
        result = list.stream().limit(50).collect(toList());
        System.out.println("统计50个"+result);
        list.forEach(System.out::println);

        list.forEach(i -> System.out.println(i));
        list.forEach((Integer i) -> System.out.println(i));

        for (int i : list) {
            System.out.println(i);
        }
    }
}
