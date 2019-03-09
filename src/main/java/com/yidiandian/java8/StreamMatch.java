package com.yidiandian.java8;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Author: 一点点
 * @Date: 2019/3/2 19:46
 * @Version 1.0
 */
public class StreamMatch {

    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        //匹配i > 10 的有没有
        boolean matched = stream.allMatch(i -> i > 10);
        System.out.println(matched);
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        matched = stream.anyMatch(i -> i > 6);
        System.out.println(matched);
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        matched = stream.noneMatch(i -> i < 0);
        System.out.println(matched);
    }
}
