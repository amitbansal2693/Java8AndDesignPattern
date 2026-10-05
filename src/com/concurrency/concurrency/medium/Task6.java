package com.concurrency.concurrency.medium;

/**
 * Task 6: Barrier Synchronization (Medium)
 * 
 * Imagine you have a class:
 * class Barrier {
 *     public void await() throws InterruptedException { ... }
 * }
 * 
 * A barrier is a synchronization primitive that causes a group of threads to wait
 * until a specified number of threads have reached it.
 * 
 * Implement a Barrier class with the following requirements:
 * - It takes a number n in the constructor, which is the number of threads that must arrive at the barrier.
 * - When the nth thread calls await(), all waiting threads (including the nth thread) should be released.
 * - After all threads are released, the barrier can be used again for subsequent rounds.
 * 
 * Example:
 * Input: n = 3, iterations = 3
 * Action: 3 threads call barrier.await() in each iteration
 * Output: All 3 threads proceed together after each reaches the barrier
 */

public class Task6 {
    
    static class Barrier {
        private int n;
        private int waiting;
        private int generation;
        
        public Barrier(int n) {
            // TODO: Initialize barrier with n threads
        }

        public void await() throws InterruptedException {
            // TODO: Implement barrier logic such that:
            // 1. Threads wait until n threads have called await()
            // 2. When nth thread arrives, all threads are released
            // 3. Barrier can be reused for multiple rounds
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int numThreads = 3;
        int iterations = 2;
        Barrier barrier = new Barrier(numThreads);

        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                for (int iter = 0; iter < iterations; iter++) {
                    System.out.println("Thread " + threadId + " reached barrier at iteration " + iter);
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread " + threadId + " passed barrier at iteration " + iter);
                }
            });
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("All threads completed!");
    }
}

