package com.concurrency.concurrency.easy;

import java.util.concurrent.CountDownLatch;

/**
 * Task 1: Print in Order (Easy)
 * 
 * ============================================================================
 * PROBLEM STATEMENT:
 * ============================================================================
 * Suppose we have a class Foo with three methods: first(), second(), and third().
 * The same instance of Foo will be passed to three different threads running 
 * concurrently. Thread A calls first(), Thread B calls second(), and Thread C 
 * calls third().
 * 
 * The challenge: These threads are fired asynchronously and can execute in 
 * ANY order. We need to enforce strict ordering: first() → second() → third()
 * regardless of the order in which the threads start executing.
 * 
 * ============================================================================
 * EXPECTED OUTPUT:
 * ============================================================================
 * Even though threads may start in random order (t2, t3, t1), the output must 
 * always be: "firstsecondthird"
 * 
 * Example:
 * If threads start as [t2, t3, t1] (second, third, first)
 * Output must still be: "firstsecondthird"
 * 
 * ============================================================================
 * SOLUTION APPROACH:
 * ============================================================================
 * Use CountDownLatch to create a waiting mechanism:
 * - firstDone: A latch that is signaled when first() completes
 * - secondDone: A latch that is signaled when second() completes
 * 
 * Execution flow:
 * 1. first() runs immediately and signals firstDone
 * 2. second() waits for firstDone, then runs, then signals secondDone
 * 3. third() waits for secondDone, then runs
 * 
 * This ensures correct ordering even if threads start out of order.
 * 
 * ============================================================================
 * TIME COMPLEXITY: O(1) per method call
 * SPACE COMPLEXITY: O(1) - only two latch objects
 * ============================================================================
 */

public class Task1 {
    
    static  class  Foo {
        // CountDownLatch to signal that first() has completed
        // When firstDone reaches 0, second() can proceed
        private final CountDownLatch firstDone;
        
        // CountDownLatch to signal that second() has completed
        // When secondDone reaches 0, third() can proceed
        private final CountDownLatch secondDone;
        
        public Foo() {
            // Initialize both latches with count = 1
            // This means they need one "countdown" call to reach 0
            firstDone = new CountDownLatch(1);
            secondDone = new CountDownLatch(1);
        }

        public void first(Runnable printFirst) throws InterruptedException {
            // first() doesn't need to wait for anything, it runs immediately
            // Execute the print action
            printFirst.run();
            
            // Signal that first() is done by decrementing the latch
            // This wakes up any threads waiting on firstDone.await()
            firstDone.countDown();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            // WAIT: Block this thread until first() has completed
            // firstDone.await() will block until firstDone reaches 0
            firstDone.await();
            
            // Now that first() is done, execute the print action
            printSecond.run();
            
            // Signal that second() is done by decrementing the latch
            // This wakes up any threads waiting on secondDone.await()
            secondDone.countDown();
        }

        public void third(Runnable printThird) throws InterruptedException {
            // WAIT: Block this thread until second() has completed
            // secondDone.await() will block until secondDone reaches 0
            secondDone.await();
            
            // Now that second() is done, execute the print action
            printThird.run();
            
            // Note: No need to signal anything after third() since it's the last method
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Create a single instance of Foo that will be shared by all threads
        Foo foo = new Foo();

        // Thread 1: Calls first()
        Thread t1 = new Thread(() -> {
            try {
                foo.first(() -> System.out.print("first"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Thread 2: Calls second()
        // This thread will block until first() completes
        Thread t2 = new Thread(() -> {
            try {
                foo.second(() -> System.out.print("second"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Thread 3: Calls third()
        // This thread will block until second() completes
        Thread t3 = new Thread(() -> {
            try {
                foo.third(() -> System.out.print("third"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Start all three threads in ANY order
        // The order of start() calls doesn't matter because of the latches
        t1.start();
        t2.start();
        t3.start();

        // Wait for all threads to complete execution
        t1.join();
        t2.join();
        t3.join();
        
        // Print a newline after all output
        // Expected output: "firstsecondthird" (always in this order, regardless of thread start order)
        System.out.println();
    }
}

