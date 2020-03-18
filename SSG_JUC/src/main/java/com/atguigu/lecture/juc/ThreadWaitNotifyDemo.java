package com.atguigu.lecture.juc;


class AirConditioner {
    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        while (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "\t " + number + "\t");
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (number != 1) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "\t " + number + "\t");
        this.notifyAll();
    }

}


/**
 * @author JiangBin
 * @create 2020-03-18 15:18
 */
public class ThreadWaitNotifyDemo {

    public static void main(String[] args) {

        AirConditioner airConditioner = new AirConditioner();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "DD").start();
    }


}
