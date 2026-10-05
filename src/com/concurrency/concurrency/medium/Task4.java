package com.concurrency.concurrency.medium;

/**
 * Task 4: Dining Philosophers (Medium)
 * 
 * Five silent philosophers sit at a round table with bowls of spaghetti.
 * Forks are placed between each pair of adjacent philosophers.
 * 
 * Each philosopher must alternately think and eat. However, a philosopher can only
 * eat spaghetti when they have both left and right forks. Each fork can be used by
 * only one philosopher at a time and must be put down by the philosopher when they
 * are finished eating.
 * 
 * Given the problem of the Dining Philosophers by Edsger Dijkstra, implement a
 * solution such that no philosopher will starve; in other words, every thread running
 * the function pickLeftFork(), pickRightFork(), eat() and putLeftFork(), putRightFork()
 * will wait indefinitely.
 * 
 * At most two philosophers can eat at the same time.
 * 
 * Required:
 * - Implement pickLeftFork(), pickRightFork(), eat(), putLeftFork(), putRightFork() methods
 * - Avoid deadlock
 * - Ensure no philosopher starves
 */

public class Task4 {
    
    private static final int NUM_PHILOSOPHERS = 5;
    
    public Task4() {
        // TODO: Initialize fork synchronization (semaphores, locks, etc.)
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                          Runnable pickLeftFork,
                          Runnable pickRightFork,
                          Runnable eat,
                          Runnable putLeftFork,
                          Runnable putRightFork) throws InterruptedException {
        // TODO: Implement philosopher eating logic
        // Must ensure:
        // 1. Pick up left fork
        // 2. Pick up right fork
        // 3. Eat
        // 4. Put down left fork
        // 5. Put down right fork
        // Without causing deadlock or starvation
    }

    public static void main(String[] args) throws InterruptedException {
        Task4 diningPhilosophers = new Task4();

        Thread[] threads = new Thread[NUM_PHILOSOPHERS];
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            final int philosopher = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 5; j++) { // Each philosopher eats 5 times
                    try {
                        diningPhilosophers.wantsToEat(
                            philosopher,
                            () -> System.out.println("Philosopher " + philosopher + " picks left fork"),
                            () -> System.out.println("Philosopher " + philosopher + " picks right fork"),
                            () -> System.out.println("Philosopher " + philosopher + " is eating"),
                            () -> System.out.println("Philosopher " + philosopher + " puts left fork"),
                            () -> System.out.println("Philosopher " + philosopher + " puts right fork")
                        );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("All philosophers finished eating!");
    }
}

