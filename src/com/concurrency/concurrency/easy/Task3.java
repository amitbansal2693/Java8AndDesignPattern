package com.concurrency.concurrency.easy;

/**
 * Task 3: FizzBuzz Multithreaded (Easy)
 * 
 * Write a program that outputs the string representation of numbers from 1 to n,
 * however:
 * 
 * - If the number is divisible by 3, output 'fizz'.
 * - If the number is divisible by 5, output 'buzz'.
 * - If the number is divisible by both 3 and 5, output 'fizzbuzz'.
 * 
 * For example, for n = 15, the expected output is:
 * 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz
 * 
 * Now suppose you are given the following code:
 * class FizzBuzz {
 *   public FizzBuzz(int n) { ... }
 *   public void fizz(Runnable printFizz) { ... }
 *   public void buzz(Runnable printBuzz) { ... }
 *   public void fizzbuzz(Runnable printFizzBuzz) { ... }
 *   public void number(IntConsumer printNumber) { ... }
 * }
 * 
 * Implement the FizzBuzz class using multithreading. Modify the program such that:
 * - One thread can call fizz() that outputs 'fizz'.
 * - One thread can call buzz() that outputs 'buzz'.
 * - One thread can call fizzbuzz() that outputs 'fizzbuzz'.
 * - One thread can call number(x) that outputs the numbers.
 * 
 * Ensure that the output of the program is correct.
 */

public class Task3 {
    
    private int n;
    
    public Task3(int n) {
        this.n = n;
        // TODO: Initialize synchronization primitives
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        // TODO: Implement fizz method
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        // TODO: Implement buzz method
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        // TODO: Implement fizzbuzz method
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        // TODO: Implement number method
    }

    @FunctionalInterface
    interface IntConsumer {
        void accept(int value);
    }

    public static void main(String[] args) throws InterruptedException {
        Task3 fizzBuzz = new Task3(15);

        Thread t1 = new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> System.out.print("fizz, "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> System.out.print("buzz, "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.print("fizzbuzz, "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t4 = new Thread(() -> {
            try {
                fizzBuzz.number(x -> System.out.print(x + ", "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Expected output: 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println();
    }
}

