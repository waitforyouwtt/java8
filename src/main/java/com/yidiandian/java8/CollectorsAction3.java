package com.yidiandian.java8;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class CollectorsAction3 {

    public  static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    public static void main(String[] args) {
        testPartitioningByWithPredicate();
        testPartitioningByWithPredicateAndCollector();
        testReducingBinaryOperator();
        testReducingBinaryOperatorAndIdentiy();
        testReducingBinaryOperatorAndIdentiyAndFunction();
        testSummarizingDouble();
        testSummarizingLong();
        testSummarizingInt();
    }

    private static void testPartitioningByWithPredicate() {
        System.out.println("testPartitioningByWithPredicate");
        Map<Boolean, List<Dish>> collect = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        Optional.of(collect).ifPresent(System.out::println);

    }

    private static void testPartitioningByWithPredicateAndCollector() {
        System.out.println("testPartitioningByWithPredicateAndCollector");
        Map<Boolean, Double> collect = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.averagingInt(Dish::getCalories)));
        Optional.of(collect).ifPresent(System.out::println);
    }

    private static void testReducingBinaryOperator() {
        System.out.println("testReducingBinaryOperator");
        menu.stream().collect(
                Collectors.reducing(
                        BinaryOperator.maxBy(
                                Comparator.comparingInt(Dish::getCalories)
                        )
                )
        ).ifPresent(System.out::println);
    }

    private static void testReducingBinaryOperatorAndIdentiy() {
        System.out.println("testReducingBinaryOperatorAndIdentiy");
        Integer result = menu.stream()
                .map(Dish::getCalories).collect(Collectors.reducing(0, (d1, d2) -> d1 + d2));
        System.out.println(result);
    }

    private static void testReducingBinaryOperatorAndIdentiyAndFunction() {
        System.out.println("testReducingBinaryOperatorAndIdentiyAndFunction");
        Integer result = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (d1, d2) -> d1 + d2));
        System.out.println(result);
    }

    private static void testSummarizingDouble() {
        System.out.println("testSummarizingDouble");
        Optional.of(menu.stream().collect(Collectors.summarizingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testSummarizingLong() {
        System.out.println("testSummarizingLong");
        Optional.of(menu.stream().collect(Collectors.summarizingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testSummarizingInt() {
        System.out.println("testSummarizingLong");
        Optional.of(menu.stream().collect(Collectors.summarizingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }
}