package com.yidiandian.java8;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: 一点点
 * @Date: 2019/3/3 11:27
 * @Version 1.0
 */
public class NumericStream {
    public static void main(String[] args) {
         Stream<Integer> stream = Arrays.stream(new Integer[]{1,2,3,4,5,6,7});
         //Integer result  = stream.filter(i -> i.intValue() > 3).reduce(0,Integer::sum);
      //  Stream<Integer> integerStream = stream.filter(i -> i.intValue() > 3);
      //  Integer result = integerStream.reduce(0,Integer::sum);
        int intStream =  stream.mapToInt(i ->i.intValue()).filter( i-> i >3).sum();
       // intStream.filter(i -> i>3).sum();
        System.out.println("得到的结果："+intStream);
        //int 4个字节，32位，integer包装类 比int 大的多

        int a = 9;

        //1..1000
        //result int[a,b,c];

        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .boxed()
                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .forEach(r -> System.out.println("a=" + r[0] + ",b=" + r[1] + ",c=" + r[2]));

        System.out.println("=======================");

        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .forEach(r -> System.out.println("a=" + r[0] + ",b=" + r[1] + ",c=" + r[2]));

    }

}
