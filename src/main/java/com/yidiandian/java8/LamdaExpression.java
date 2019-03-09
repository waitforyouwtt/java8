package com.yidiandian.java8;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Author: 一点点
 * @Date: 2019/2/17 10:17
 * @Version 1.0
 */
public class LamdaExpression {

    public static void main(String[] args) {

        Comparator<Apple> byColor = new Comparator<Apple>(){
            @Override
            public int compare(Apple o1,Apple o2){
              return o1.getColor().compareTo(o2.getColor());
            };
        };
        List<Apple> list = Collections.emptyList();
        list.sort(byColor);
        //有大括号必须要return ,无大括号无需要return
        Comparator<Apple> byColor2 = ((o1, o2) -> o1.getColor().compareTo(o2.getColor()));
        Comparator<Apple> byColor3 = ((o1, o2) -> {return o1.getColor().compareTo(o2.getColor());});

        Function<String,Integer> fLamda = s ->s.length();
        Predicate<Apple> predicate = (Apple a) -> a.getColor().equals("green");





    }
}
