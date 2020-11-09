package com.ab.week03;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @classname:
 * @description:
 * @author: sunxinbo
 * @time: 2020/11/9、21:44
 */
public class ThreadCallback {

    private static Integer threadCount = 1;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        callableTest();
        futureTest();
        cyclicBarrierTest();
        countDownLatchTest();
    }

    /**
     * @description 线程池 submit Callable 方式获取返回值
     * @param
     * @return void
     * @author sunxinbo
     * @time 2020/11/9 22:12
     */
    private static void callableTest() throws ExecutionException, InterruptedException {
        System.out.println("---------------callableTest--------------");
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<String> submit = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " sleep。。。。。。");
                Thread.sleep(1000);
                return "hello";
            }
        });
        String s = submit.get();
        System.out.println(s);
        executorService.shutdown();
    }

    /**
     * @description futureTask 方式获取返回值
     * @param
     * @return void
     * @author sunxinbo
     * @time 2020/11/9 22:15
     */
    private static void futureTest() throws ExecutionException, InterruptedException {
        System.out.println("-------------futureTest--------------");
        FutureTask<String> task = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " sleep。。。。。。");
                Thread.sleep(1000);
                return "world";
            }
        });
        Thread t = new Thread(task);
        t.start();
        System.out.println(task.get());
    }

    /**
     * @description cyclicBarrier 使用最后一个线程执行 cyclicBarrier 内的代码
     * @param
     * @return void
     * @author sunxinbo
     * @time 2020/11/9 22:36
     */
    private static void cyclicBarrierTest(){
        System.out.println("----------CyclicBarrierTest-------------------");
//        模拟返回值
        final AtomicInteger integer = new AtomicInteger(0);
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() +  " integer = "+integer);
            }
        });
        for (int i = 0; i < threadCount; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                        System.out.println( Thread.currentThread().getName() + " run.....");
                        integer.addAndGet(1);
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }

//        cyclicBarrierTest 可以重复使用
//        for (int i = 0; i < threadCount; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(500);
//                        System.out.println( Thread.currentThread().getName() + " 2 run.....");
//                        integer.addAndGet(1);
//                        cyclicBarrier.await();
//                    } catch (InterruptedException e) {
//                    } catch (BrokenBarrierException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }).start();
//        }
    }

    /**
     * @description: 使用 CountDownLatch 控制主线程最后获取值
     * @param 
     * @return void
     * @author sunxinbo
     * @time 2020/11/9 22:45
     */   
    private static void countDownLatchTest() throws InterruptedException {
        System.out.println("----------------countDownLatchTest---------------------");
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
//        模拟返回值
        final AtomicInteger integer = new AtomicInteger(0);
        for (int i = 0; i < threadCount; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " sleeping.....");
                    try {
                        Thread.sleep(500);
                        integer.addAndGet(1);
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {

                    }
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " integer = " + integer);
    }
}
