package com.yidiandian.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: 一点点
 * @Date: 2019/2/17 9:18
 * @Version 1.0
 */
public class FilterApple {

    public static List<Apple> findGreenApple(List<Apple> apples){
        List<Apple> list = new ArrayList<>();
        for (Apple apple:apples){
            if("green".equals(apple.getColor())){
                list.add(apple);
            }
        }
        return list;
    }

    public static List<Apple> findApply(List<Apple> apples,String color){
        List<Apple> list = new ArrayList<>();
        for (Apple apple:apples){
            if(color.equals(apple.getColor())){
                list.add(apple);
            }
        }
        return list;
    }

    /**
     * 策略模式
     * 如果一个接口只有一个方法，那么它就是Functional接口
     */
    @FunctionalInterface
    public static interface AppleFilter{
        boolean filter(Apple apple);
    }
    public static List<Apple> findApple(List<Apple> apples,AppleFilter appleFilter){
        List<Apple> list = new ArrayList<>();
        for (Apple apple:apples){
            if (appleFilter.filter(apple)){
                list.add(apple);
            }
        }
        return list;
    }
    public static class GreenAnd150WeightFilter implements AppleFilter{
        @Override
        public boolean filter(Apple apple) {
            return (apple.getColor().equals("green") && apple.getWeight() > 100);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Apple> list = Arrays.asList(new Apple("green",150),new Apple("red",60),new Apple("green",100));
       // List<Apple> greenApples = findGreenApple(list);
       // System.out.println("普通得到的结果："+greenApples);
        System.out.println("---------------------");
        List<Apple> result = findApple(list,new GreenAnd150WeightFilter());
        System.out.println("策略得到的结果："+result);
        System.out.println("---------------------");
        List<Apple> redList = findApple(list, new AppleFilter() {
            @Override
            public boolean filter(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });
        System.out.println("匿名内部类得到的结果："+redList);
        System.out.println("---------------------");
        List<Apple> lambdaResult =  findApple(list,(Apple apple)->{
           return apple.getColor().equals("green");
        });
        System.out.println("lamda得到的结果："+lambdaResult);

        //Runnable 就是Functional 接口
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程的名字："+Thread.currentThread().getName());
            }
        });
        thread.start();

        System.out.println("--------------------------");
        //使用lamda 创建线程
        new Thread(()->System.out.println(Thread.currentThread().getName())).start();
        //线程等待，也可以用wait
        Thread.currentThread().join();



    }
}
