package com.concurrency.concurrency.hard;

import java.util.*;

/**
 * Task 8: Thread Pool / Executor Service (Hard)
 * 
 * Implement a simple thread pool (executor service) that:
 * 
 * 1. Takes a fixed number of worker threads
 * 2. Accepts tasks (Runnable objects) to be executed
 * 3. Distributes tasks to available worker threads
 * 4. Queues tasks if no worker threads are available
 * 5. Handles graceful shutdown
 * 
 * Requirements:
 * - Thread-safe task queue
 * - Proper synchronization between workers and task queue
 * - Support for submit(Runnable) to add tasks
 * - Support for shutdown() to stop accepting new tasks
 * - Support for awaitTermination() to wait for all tasks to complete
 * 
 * Example:
 * Input: 3 worker threads, 10 tasks
 * Output: All tasks executed by available workers, some queued if necessary
 */

public class Task8 {
    
    static class SimpleThreadPool {
        private Queue<Runnable> taskQueue;
        private List<Worker> workers;
        private boolean shutdown;
        
        public SimpleThreadPool(int numWorkers) {
            // TODO: Initialize thread pool with numWorkers worker threads
            // TODO: Create worker threads and start them
        }

        public void submit(Runnable task) throws IllegalStateException {
            // TODO: Implement task submission
            // TODO: Add task to queue
            // TODO: Notify waiting workers
            // TODO: Throw IllegalStateException if shutdown
        }

        public void shutdown() {
            // TODO: Implement shutdown
            // TODO: Stop accepting new tasks
            // TODO: Allow existing tasks to complete
        }

        public boolean awaitTermination(long timeout) throws InterruptedException {
            // TODO: Implement termination wait
            // TODO: Wait for all workers to complete their tasks
            // TODO: Return true if all completed within timeout, false otherwise
            return true;
        }

        class Worker extends Thread {
            public void run() {
                // TODO: Implement worker loop
                // TODO: Wait for tasks from queue
                // TODO: Execute tasks
                // TODO: Exit when shutdown and queue is empty
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int numWorkers = 3;
        int numTasks = 10;
        SimpleThreadPool pool = new SimpleThreadPool(numWorkers);

        // Submit tasks
        for (int i = 0; i < numTasks; i++) {
            final int taskId = i;
            pool.submit(() -> {
                System.out.println("Task " + taskId + " executed by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(100); // Simulate work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Shutdown pool
        pool.shutdown();

        // Wait for all tasks to complete
        boolean completed = pool.awaitTermination(30);
        System.out.println("All tasks completed: " + completed);
    }
}

