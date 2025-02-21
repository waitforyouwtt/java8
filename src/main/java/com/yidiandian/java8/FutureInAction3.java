package com.yidiandian.java8;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: 一点点
 * @Date: 2019/5/12 20:24
 * @Version 1.0
 */
public class FutureInAction3 {
    public static void main(String[] args) {
        Future<String> future = invoke(() -> {
            try {
                Thread.sleep(10000L);
                return "I am Finished.";
            } catch (InterruptedException e) {
                return "I am Error";
            }
        });

        future.setCompletable(new Completable<String>() {
            @Override
            public void complete(String s) {
                System.out.println(s);
            }

            @Override
            public void exception(Throwable cause) {
                System.out.println("error");
                cause.printStackTrace();
            }
        });
        System.out.println(".........");
        System.out.println(future.get());
        System.out.println(future.get());

    }

    private static <T> Future<T> invoke(Callable<T> callable){
        AtomicReference<T> result = new AtomicReference<>(  );
        AtomicBoolean finished = new AtomicBoolean(  );

        Future<T> future  = new Future<T>() {

            private Completable<T> completable;
            @Override
            public T get() {
                return result.get();
            }

            @Override
            public boolean isDown() {
                return finished.get();
            }

            @Override
            public void setCompletable(Completable<T> completable) {
                this.completable = completable;
            }

            @Override
            public Completable<T> getCompletable() {
                return completable;
            }


        };

        Thread thread = new Thread( () ->{
            try {
                T value = callable.action();
                result.set(value);
                finished.set(true);
                if (future.getCompletable() != null){
                    future.getCompletable().complete(value);
                }
            } catch (Throwable cause) {
                if (future.getCompletable() != null){
                    future.getCompletable().exception(cause);
                }
            }
        } );
        thread.start();

        return future;
    }

    private interface Future<T> {
        T get();
        boolean isDown();
        void setCompletable(Completable<T> completable);
        Completable <T> getCompletable();
    }
    private interface Callable<T>{
        T action();
    }
    private interface Completable<T> {
        void complete(T t);
        void exception(Throwable cause);
    }
}
