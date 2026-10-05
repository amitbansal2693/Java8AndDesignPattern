package com.concurrency.concurrency.medium;

import java.util.*;

/**
 * Task 7: Thread-Safe Counter (Medium)
 * 
 * Design a thread-safe counter that supports the following operations:
 * 
 * 1. increment() - Increment the counter by 1
 * 2. decrement() - Decrement the counter by 1
 * 3. get() - Return the current value of the counter
 * 
 * The counter must be safe to use with multiple threads accessing it simultaneously.
 * No data races should occur, and the counter should maintain its integrity.
 * 
 * You should optimize for the case where there are many readers and few writers
 * (or vice versa).
 * 
 * Example:
 * Input:
 *   - 5 threads incrementing 10 times each
 *   - 3 threads decrementing 5 times each
 *   - Final value should be: (5 * 10) - (3 * 5) = 35
 */

public class Task7 {
    
    static class ThreadSafeCounter {
        // TODO: Add fields for counter synchronization
        private long value;
        
        public ThreadSafeCounter(long initialValue) {
            this.value = initialValue;
            // TODO: Initialize any locks or synchronization primitives
        }

        public void increment() {
            // TODO: Implement increment with proper synchronization
        }

        public void decrement() {
            // TODO: Implement decrement with proper synchronization
        }

        public long get() {
            // TODO: Implement get with proper synchronization
            return value;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeCounter counter = new ThreadSafeCounter(0);
        int numIncrementThreads = 5;
        int numDecrementThreads = 3;
        int incrementsPerThread = 10;
        int decrementsPerThread = 5;

        Thread[] incrementThreads = new Thread[numIncrementThreads];
        Thread[] decrementThreads = new Thread[numDecrementThreads];

        // Create increment threads
        for (int i = 0; i < numIncrementThreads; i++) {
            final int threadId = i;
            incrementThreads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    counter.increment();
                }
                System.out.println("Increment thread " + threadId + " finished");
            });
        }

        // Create decrement threads
        for (int i = 0; i < numDecrementThreads; i++) {
            final int threadId = i;
            decrementThreads[i] = new Thread(() -> {
                for (int j = 0; j < decrementsPerThread; j++) {
                    counter.decrement();
                }
                System.out.println("Decrement thread " + threadId + " finished");
            });
        }

        // Start all threads
        for (Thread t : incrementThreads) {
            t.start();
        }
        for (Thread t : decrementThreads) {
            t.start();
        }

        // Wait for all threads to complete
        for (Thread t : incrementThreads) {
            t.join();
        }
        for (Thread t : decrementThreads) {
            t.join();
        }

        long expectedValue = (long) numIncrementThreads * incrementsPerThread 
                           - (long) numDecrementThreads * decrementsPerThread;
        long actualValue = counter.get();
        
        System.out.println("Expected value: " + expectedValue);
        System.out.println("Actual value: " + actualValue);
        System.out.println("Test " + (expectedValue == actualValue ? "PASSED" : "FAILED"));
    }
}

