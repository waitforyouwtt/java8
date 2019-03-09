package com.yidiandian.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @Author: 一点点
 * @Date: 2019/2/17 16:41
 * @Version 1.0
 */
public class MethodReference {

    private static<T> void useConsumer(Consumer<T> consumer, T t){
      consumer.accept(t);
      consumer.accept(t);
    }

    public static void main(String[] args) {
       Consumer<String> consumer = (s)-> System.out.println(s);
       useConsumer(consumer,"hello alex");
       useConsumer(s-> System.out.println(s),"hello yidiandian");
       List<Apple> list = Arrays.asList(new Apple("green",150),new Apple("red",60),new Apple("green",100));
       list.sort((o1,o2)-> o1.getColor().compareTo(o2.getColor()));
        System.out.println(list);
        list.stream().forEach(a -> System.out.println(a));
        list.stream().forEach(System.out::println);
        int value = Integer.parseInt("123");
        Function<String,Integer> f = Integer::parseInt;
        Integer result = f.apply("123");

        list.sort(Comparator.comparing(Apple::getWeight));
        System.out.println("得到的结果："+list);

        BiFunction<String,Integer,Character> f2 = String::charAt;
        Character c =  f2.apply("hello",2);
        System.out.println("得到字符串第二位的值[下标从0开始]："+c);

        String string = new String("hello");
        Function<Integer,Character> f3 = string::charAt;
        Character c2 = f3.apply(4);
        System.out.println("string得到的结果："+c2);
    }
}
