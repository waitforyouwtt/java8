package com.yidiandian;

import com.google.common.base.Joiner;
import com.yidiandian.java8.Apple;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Java8ApplicationTests {

    static List<Apple> appleList = Arrays.asList(
              new Apple("green", 170)
            , new Apple("green", 150)
            , new Apple("yellow", 150)
           /* , new Apple("green", 170)
            , new Apple("yellow", 120)
            , new Apple("green", 170)*/
    );


    @Test
    public void ListTest() {
        List<Apple> greenList = appleList.stream().filter(apple -> apple.getColor().equals("green")).collect( Collectors.toList());
        log.info( "集合根据某一属性进行筛选：{}",greenList );
        Map<String, List<Apple>> groupingByColors = appleList.parallelStream().collect( groupingBy( Apple::getColor ) );
        log.info( "集合根据某一属性进行分组：{}",groupingByColors );
        Double avgValueDouble = appleList.stream().collect( Collectors.averagingDouble( Apple::getWeight ) );
       // int avgValueInt = appleList.stream().collect( Collectors.averagingInt( Apple::getWeight ) );
        log.info( "集合根据某一属性进行求取平均值：{}",avgValueDouble );
        String avgAfterAppend = appleList.stream().collect( Collectors.collectingAndThen( Collectors.averagingInt( Apple::getWeight ), a -> "The Average Calories is->" + a ) );
        log.info( "集合根据某一属性进行操作，然后再追加值：{}",avgAfterAppend );
        Long count = appleList.stream().collect( Collectors.counting() );
        log.info( "集合的个数统计：{}",count );
        Map<String, Double> groupAfterAvg = appleList.stream().collect( groupingBy( Apple::getColor, Collectors.averagingInt( Apple::getWeight ) ) );
        log.info( "集合分组后求平均值：{}",groupAfterAvg );
        TreeMap<String, Double> groupAfterOrder = appleList.stream().collect( groupingBy( Apple::getColor, TreeMap::new, Collectors.averagingInt( Apple::getWeight ) ) );
        log.info( "集合分组后求平均值加排序：{}",groupAfterOrder );
        IntSummaryStatistics result = appleList.stream().collect(Collectors.summarizingInt(Apple::getWeight));
        log.info( "集合按照某一属性求各种聚合函数：集合的size:{},集合平均值：{},集合相加：{},集合的最大值：{},集合最小值：{}",
                result.getCount(),result.getAverage(),result.getSum(),result.getMax(),result.getMax(),result.getMin() );

        ConcurrentMap<String, List<Apple>> groupList = appleList.stream().collect( Collectors.groupingByConcurrent( Apple::getColor ) );
        log.info( "集合按照某一属性分组，并且每组都有哪些集合：{}",groupList );

        ConcurrentMap<String, Double> groupAfterAvg2 = appleList.stream().collect( Collectors.groupingByConcurrent( Apple::getColor, Collectors.averagingInt( Apple::getWeight ) ) );
        log.info( "集合按照某一属性分组，且求平均值：{}",groupAfterAvg2 );
        ConcurrentSkipListMap<String, Double> groupAfterAvg3 = appleList.stream().collect( Collectors.groupingByConcurrent( Apple::getColor, ConcurrentSkipListMap::new, Collectors.averagingInt( Apple::getWeight ) ) );
        log.info( "集合按照某一属性分组，且求平均值：{}",groupAfterAvg3 );
        Map<String, Double> groupAfterAvg4 = appleList.stream().collect(Collectors.groupingBy(Apple::getColor, Collectors.averagingInt(Apple::getWeight)));
        log.info( "集合按照某一属性分组，且求平均值：{}",groupAfterAvg4 );

        String join = appleList.stream().map( Apple::getColor ).collect( Collectors.joining() );
        log.info( "集合按照某一属性直接拼接：{}",join );
        String join1 = appleList.stream().map(Apple::getColor).collect(Collectors.joining(","));
        log.info( "集合按照某一属性按照逗号拼接：{}",join1 );
        String join2 = appleList.stream().map(Apple::getColor).collect(Collectors.joining(",", "Names[", "]"));
        log.info( "集合按照某一属性按照逗号拼接且加上前后缀：{}",join2 );
        String join3 = appleList.stream().collect(Collectors.mapping(Apple::getColor, Collectors.joining(",")));
        log.info( "集合按照某一属性按照逗号拼接：{}",join3 );
        Optional<Apple> filterMax = appleList.stream().collect( Collectors.maxBy( Comparator.comparingInt( Apple::getWeight ) ) );
        log.info( "集合按照某一属性取得最大值：{}",filterMax );
        Optional<Apple> maxfileter = appleList.stream().collect(Collectors.maxBy(Comparator.comparing(Apple:: getWeight )));
        log.info("筛选集合中重量最大的：{}",maxfileter);
        List<String> strings = appleList.stream().map(Apple::getColor).collect(Collectors.toList());
        log.info("集合中的某一列：{}",strings);
        //集合按照某列反转排序
        appleList.sort(Comparator.comparing(Apple::getWeight).reversed());
        Integer listSize = appleList.stream().collect(Collectors.collectingAndThen(toList(), t -> t.size()));
        log.info("获取集合的size：{}",listSize);


    }

    @Test
    public void revertString(){
        String str= "i love china";
        List<String> list = Arrays.asList(str.split(" "));
        Collections.reverse(list);
        String reverseStr = Joiner.on(" ").join( list );
        log.info("反转字符串得到的结果：{}",reverseStr);

    }

    @Test
    public void mapIteratorTest(){
        Map<String, List<Apple>> collect = appleList.stream().collect(Collectors.groupingBy(Apple::getColor));
        collect.forEach((k,v)->{
            if (k.equals("yellow")){
                log.info("得到的K:{}",k);
                return;
            }
            log.info("跳出循环吗？？？");
        });
    }

    /**
     * 根据某字段进行分组,取出每一组中最大的值
     */
    @Test
    public void arrayGroupByFiledFilterMaxTest() {
        Map<String, List<Apple>> map = appleList.stream().collect(groupingBy(Apple::getColor));
        map.forEach((k, v) -> {
            Optional<Apple> collect = v.stream().collect(Collectors.maxBy(Comparator.comparing(Apple::getWeight)));
            if (collect.isPresent()) {
                Apple phone = collect.get();
                log.info("查找一组数据中最大的值:{}", phone);
            }
        });
    }

}
