package com.yidiandian.java8;

import java.util.concurrent.*;

/**
 * @Author: 一点点
 * @Date: 2019/5/12 18:50
 * @Version 1.0
 */
public class FutureInAction2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit( ()->{
            try {
                Thread.sleep( 10000L );
                return "i am finished";
            } catch (InterruptedException e) {
                return "error";
            }
        } );
        //String value = future.get();
        //加参数，等10秒，超过10秒不管你执行完没都返回
        String value = future.get(10, TimeUnit.MICROSECONDS);
        //超过10秒，宝宝再等10秒
        while (!future.isDone()) {
            Thread.sleep(10);
        }
        System.out.println(value);
        executorService.shutdown();
    }
}
