package com.yidiandian.java8;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Author: 一点点
 * @Date: 2019/3/2 20:06
 * @Version 1.0
 */
public class StreamFind {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 5, 6, 5, 7});
        Optional<Integer> optional1 = stream.filter(i -> i % 2 == 0).findAny();
        System.out.println("得到的符合的数据："+optional1.get());
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        Optional<Integer> optional3 = stream.filter(i -> i < 10).findAny();
        System.out.println(optional3.orElse(-1));

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        Optional<Integer> optional2 = stream.filter(i -> i % 2 == 0).findFirst();
        optional2.ifPresent(System.out::println);
        System.out.println(optional2.filter(i -> i == 2).get());

    }
}
