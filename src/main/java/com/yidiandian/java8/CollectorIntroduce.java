package com.yidiandian.java8;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * @Author: 一点点
 * @Date: 2019/3/3 20:30
 * @Version 1.0
 */
public class CollectorIntroduce {

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("green", 150)
                , new Apple("yellow", 120)
                , new Apple("green", 170)
                , new Apple("green", 150)
                , new Apple("yellow", 120)
                , new Apple("green", 170));

        List<Apple> greenList = list.stream().filter(apple -> apple.getColor().equals("green")).collect(Collectors.toList());
        //筛选出颜色为绿色的苹果
        Optional.ofNullable(greenList).ifPresent(System.out::println);
        //用颜色进行分组的三种方法
        Optional.ofNullable(groupByNormal(list)).ifPresent(System.out::println);
        System.out.println("===================================================");
        Optional.ofNullable(groupByFunction(list)).ifPresent(System.out::println);
        System.out.println("===================================================");
        Optional.ofNullable(groupByCollector(list)).ifPresent(System.out::println);

    }

    private static Map<String, List<Apple>> groupByNormal(List<Apple> apples) {
        Map<String, List<Apple>> map = new HashMap<>();
        for (Apple a : apples) {
            List<Apple> list = map.get(a.getColor());
            if (null == list) {
                list = new ArrayList<>();
                map.put(a.getColor(), list);
            }
            list.add(a);
        }
        return map;
    }

    private static Map<String, List<Apple>> groupByFunction(List<Apple> apples) {
        Map<String, List<Apple>> map = new HashMap<>();
        apples.parallelStream().forEach(a -> {
            List<Apple> colorList = Optional.ofNullable(map.get(a.getColor())).orElseGet(() -> {
                List<Apple> list = new ArrayList<>();
                map.put(a.getColor(), list);
                return list;
            });
            colorList.add(a);
        });
        return map;
    }

    private static Map<String, List<Apple>> groupByCollector(List<Apple> apples) {
        return apples.parallelStream().collect(groupingBy(Apple::getColor));
    }



}
