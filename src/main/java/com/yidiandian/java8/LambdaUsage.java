package com.yidiandian.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * @Author: 一点点
 * @Date: 2019/2/17 11:05
 * @Version 1.0
 */
public class LambdaUsage {
    private static List<Apple> filter(List<Apple> source, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : source) {
            if (predicate.test(a))
                result.add(a);
        }
        return result;
    }
    private static List<Apple> filterByWeight(List<Apple> source, LongPredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : source) {
            if (predicate.test(a.getWeight()))
                result.add(a);
        }
        return result;
    }
    private static List<Apple> filterByBiPredicate(List<Apple> source, BiPredicate<String,Integer> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : source) {
            if (predicate.test(a.getColor(), a.getWeight()))
                result.add(a);
        }
        return result;
    }
    private static void simpleTestConsumer(List<Apple> source, Consumer<Apple> consumer) {
        for (Apple a : source) {
            consumer.accept(a);
        }
    }
    private static void simpleBiConsumer(String c, List<Apple> source, BiConsumer<Apple, String> consumer) {
        for (Apple a : source) {
            consumer.accept(a, c);
        }
    }

    private static String testFunction(Apple apple, Function<Apple, String> fun) {
        return fun.apply(apple);
    }

    private static Apple testBiFunction(String color, long weight, BiFunction<String, Long, Apple> fun) {
        return fun.apply(color, weight);
    }



    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("green", 120), new Apple("red", 150));
        List<Apple> greenList = filter(list, (apple) -> apple.getColor().equals("i"));
        System.out.println("得到的结果："+greenList);
        List<Apple> result2 = filterByBiPredicate(list, (s, w) -> s.equals("green") && w > 100);
        System.out.println("两个参数的结果"+result2);
    }
}
