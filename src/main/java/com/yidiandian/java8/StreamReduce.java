package com.yidiandian.java8;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Author: 一点点
 * @Date: 2019/3/2 21:01
 * @Version 1.0
 * 聚合函数
 */
public class StreamReduce {

    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        Integer result = stream.reduce(0, Integer::sum);
        System.out.println("求和得到的结果："+result);
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        Integer result2 =stream.reduce((i, j) -> i + j).get();
        System.out.println("求和得到的结果："+result2);
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream.reduce(Integer::max).ifPresent(System.out::println);
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream.reduce(Integer::min).ifPresent(System.out::println);
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream.reduce((i, j) -> i > j ? j : i).ifPresent(System.out::println);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        int result3 = stream.filter(i -> i % 2 == 0).reduce(1, (i, j) -> i * j);

        Optional.of(result3).ifPresent(System.out::println);

    }
}
