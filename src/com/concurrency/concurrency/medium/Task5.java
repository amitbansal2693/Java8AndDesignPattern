package com.concurrency.concurrency.medium;

/**
 * Task 5: Print in Different Order (Medium)
 * 
 * You are given a method printNumber that can be called with an integer parameter
 * and prints it.
 * 
 * Given an integer n, you need to call printNumber with the numbers from 1 to n,
 * but the order in which 1 to n are printed can be in a different order based on 
 * which thread calls the printNumber method.
 * 
 * Given a string s of '1' and '2s where '1' represents that printNumber(1) is called
 * before printNumber(2) is should be called, return true if printNumber is called in 
 * the correct order for different threads.
 * 
 * This problem requires implementing a solution where multiple threads can call print()
 * with numbers, and you need to ensure deterministic ordering.
 * 
 * Example 1:
 * Input: s = "12", n = 2
 * Output: true
 * Explanation: Thread 1 calls printNumber(1), and thread 2 calls printNumber(2).
 * They can both run asynchronously.
 *
 * Example 2:
 * Input: s = "21", n = 2
 * Output: false
 * Explanation: Thread 1 calls printNumber(2), and thread 2 calls printNumber(1).
 * They can both run asynchronously. But printNumber(2) should be called before printNumber(1).
 */

public class Task5 {
    
    private int n;
    private String order;
    
    public Task5(int n, String order) {
        this.n = n;
        this.order = order;
        // TODO: Initialize synchronization primitives
    }

    // call the run() method to execute its code
    public void printInOrder(int number, Runnable printNumber) throws InterruptedException {
        // TODO: Implement method to ensure numbers are printed in correct order
        // based on the order string
    }

    public static void main(String[] args) throws InterruptedException {
        // Example: "12" means printNumber(1) should print before printNumber(2)
        Task5 solution = new Task5(2, "12");

        Thread t1 = new Thread(() -> {
            try {
                solution.printInOrder(1, () -> System.out.print("1"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                solution.printInOrder(2, () -> System.out.print("2"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Start threads in reverse order - they should still print in correct order
        t2.start();
        t1.start();

        t1.join();
        t2.join();

        System.out.println();
    }
}

