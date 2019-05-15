package com.yidiandian.java8;

import com.sun.org.apache.regexp.internal.RE;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: 一点点
 * @Date: 2019/5/12 18:18
 * @Version 1.0
 */
public class FutureInAction {

    public static void main(String[] args) throws InterruptedException {
     /* Future<String> future = invoke( ()->{
          try {
              Thread.sleep( 10000 );
              return "i am finished";
          } catch (InterruptedException e) {
              return "error";
          }
      } );
        System.out.println(future.get());
        System.out.println(future.get());
        System.out.println(future.get());
        while(!future.isDown()){
            Thread.sleep( 10 );
        }
        System.out.println(future.get());*/

        String value = block(() -> {
            try {
                Thread.sleep(10000);
                return "I am finished.";
            } catch (InterruptedException e) {
                return "Error";
            }
        });
        System.out.println(value);

    }
    private static <T> T block(Callable<T> callable) {
        return callable.action();
    }

    private static <T> Future<T> invoke(Callable<T> callable){
        AtomicReference<T> result = new AtomicReference<>(  );
        AtomicBoolean finished = new AtomicBoolean(  );
        Thread thread = new Thread( () ->{
           T value = callable.action();
           result.set( value);
           finished.set( true );
       } );
        thread.start();
        Future<T> future  = new Future<T>() {
            @Override
            public T get() {
                return result.get();
            }

            @Override
            public boolean isDown() {
                return finished.get();
            }

        };
        return future;
    }

    private interface Future<T> {
        T get();
        boolean isDown();
    }
    private interface Callable<T>{
        T action();
    }
}
