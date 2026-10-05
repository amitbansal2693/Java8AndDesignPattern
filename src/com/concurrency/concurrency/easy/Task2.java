package com.concurrency.concurrency.easy;

import java.util.concurrent.Semaphore;

/**
 * Task 2: ZeroEvenOdd (Easy)
 * 
 * Suppose you are given the following function:
 * void printNumber(int x);
 * 
 * This function will output a given integer each time it is called.
 * For example, printNumber(7) will output 7.
 * 
 * Now you will be given an instance of the class ZeroEvenOdd that has three functions
 * divided into three different threads:
 * 
 * 1. zero(printNumber) - outputs 0 every time.
 * 2. even(printNumber) - outputs all even numbers from 2 to n in increasing order.
 * 3. odd(printNumber) - outputs all odd numbers from 1 to n in increasing order.
 * 
 * The same instance of ZeroEvenOdd will be passed to three different threads.
 * Thread A will call zero(printNumber), thread B will call even(printNumber), and 
 * thread C will call odd(printNumber).
 * 
 * Modify the given program to output the series "010203040506..." where the length of the series
 * is 2n (n is a given parameter).
 * 
 * Example 1:
 * Input: n = 2
 * Output: "0102"
 * Explanation: There are three threads being fired asynchronously. One of them calls zero(),
 * the other calls even(), and the last calls odd(). "zero()" should print 0. Since x == 0,
 * only the even thread is expected to print. After the even thread prints "2", the odd thread
 * is expected to print. ZeroEvenOdd().zero(printNumber) outputs "0" like so "0". However,
 * in this problem, the order in which threads are scheduled is unpredictable.
 */

public class Task2 {
    
    private int n;
    
    public Task2(int n) {
        this.n = n;
        // TODO: Initialize synchronization primitives
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        // TODO: Implement zero method - print 0, n times
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        // TODO: Implement even method - print 2, 4, 6, ..., n (if n is even)
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        // TODO: Implement odd method - print 1, 3, 5, ..., n (if n is odd)
    }

    @FunctionalInterface
    interface IntConsumer {
        void accept(int value);
    }

    public static void main(String[] args) throws InterruptedException {
        Task2 zeroEvenOdd = new Task2(3);

        Thread t1 = new Thread(() -> {
            try {
                zeroEvenOdd.zero(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                zeroEvenOdd.even(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                zeroEvenOdd.odd(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Test: output should form pattern "0102030405..." etc
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println();
    }
}

