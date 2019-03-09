package com.yidiandian.java8;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @Author: 一点点
 * @Date: 2019/3/4 9:30
 * @Version 1.0
 */
public class ParallelProcessing {
    public static void main(String[] args) {
        //通过命令行查看cpu：dxdiag ---回撤
        //通过java 查看cpu 数
        //System.out.println(Runtime.getRuntime().availableProcessors());

        System.out.println("The best process time(normalAdd java8)=>" + measureSumPerformance(ParallelProcessing::normalAdd, 100_000_000) + " MS");

        System.out.println("The best process time(iterateStream 串行)=>" + measureSumPerformance(ParallelProcessing::iterateStream, 10_000_000) + " MS");

        //System.out.println("The best process time(parallelStream 并行)=>" + measureSumPerformance(ParallelProcessing::parallelStream, 10_000_000) + " MS");

        //System.out.println("The best process time(parallelStream 拆箱并行)=>" + measureSumPerformance(ParallelProcessing::parallelStream2, 10_000_000) + " MS");

        System.out.println("The best process time(parallelStream3)=>" + measureSumPerformance(ParallelProcessing::parallelStream3, 100_000_000) + " MS");
    }

    private static long measureSumPerformance(Function<Long, Long> adder, long limit) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long startTimestamp = System.currentTimeMillis();
            long result = adder.apply(limit);
            long duration = System.currentTimeMillis() - startTimestamp;
//            System.out.println("The result of sum=>" + result);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }
    private static long iterateStream(long limit) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStream(long limit) {
        return Stream.iterate(1L, i -> i + 1).parallel()
                .limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStream2(long limit) {
        return Stream.iterate(1L, i -> i + 1).mapToLong(Long::longValue).parallel()
                .limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStream3(long limit) {
        return LongStream.rangeClosed(1, limit).parallel().reduce(0L, Long::sum);
    }

    private static long normalAdd(long limit) {
        long result = 0L;
        for (long i = 1L; i < limit; i++) {
            result += i;
        }
        return result;
    }
}
