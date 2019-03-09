package com.yidiandian.java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * @Author: 一点点
 * @Date: 2019/3/2 16:15
 * @Version 1.0
 */
public class SimpleStream {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));


       /* List<String> dishNamesByCollections =getByCollections(menu);
        System.out.println("利用collections得到的结果："+dishNamesByCollections);*/

        //启动mian 函数，借助jdk 查看线程： cmd --jconsole --选中main 函数--最下面点击连接---线程---signal
        //forkjoinPool ，把一个线程分成几份，由此看到做到了并发处理
       /* List<String> dishNamesByStream =getDishNameByStream(menu);
        System.out.println("利用Stream得到的结果："+dishNamesByStream);*/

       //将对象变成流,去掉重复的
        Stream<Dish> dishStream = Stream.of(new Dish("salmon", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 300, Dish.Type.FISH)).distinct();
        System.out.println("将对象变成流：");
        dishStream.forEach(System.out::println);

        List<String> result = menu.stream().filter(d -> {
            System.out.println("filtering->" + d.getName());
            return d.getCalories() > 300;
        }).map(d -> {
                    System.out.println("map->" + d.getName());
                    return d.getName();
        }).limit(3).collect(toList());
    }

    private static List<String> getDishNameByStream(List<Dish> menu){
        //并行处理 parallelStream();
       /* return  menu.stream().filter(d -> d.getCalories() < 400).
                sorted(Comparator.comparing(Dish::getCalories)).map(Dish::getName).collect(Collectors.toList());*/
       return  menu.parallelStream().filter(d->{
           try {
               Thread.sleep(100000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           return d.getCalories() < 400;
       }).sorted(comparing(Dish::getCalories)).map(Dish::getName).collect(toList());
    }

    private static List<String> getByCollections(List<Dish> menu){
        List<Dish> dishes = new ArrayList<>();
        for (Dish d: menu){
            if(d.getCalories() < 400){
                dishes.add(d);
            }
        }
        Collections.sort(dishes,(d1,d2)->Integer.compare(d1.getCalories(),d2.getCalories()));
        //1000 即1秒 ，下面是100秒
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<String> strings = new ArrayList<>();
        for (Dish d: dishes){
            strings.add(d.getName());
        }
        return strings;
    }


}
