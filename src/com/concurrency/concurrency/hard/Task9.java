package com.concurrency.concurrency.hard;

import java.util.*;

/**
 * Task 9: Producer-Consumer Pattern (Hard)
 * 
 * Implement a producer-consumer problem using a bounded buffer.
 * 
 * Requirements:
 * 1. Multiple producer threads add items to a buffer
 * 2. Multiple consumer threads remove items from the buffer
 * 3. Buffer has a fixed size (e.g., capacity of 5)
 * 4. Producers wait if buffer is full
 * 5. Consumers wait if buffer is empty
 * 6. Thread-safe operations with proper synchronization
 * 
 * Design a BoundedBuffer<T> class that supports:
 * - put(T item) - Add item to buffer (blocks if full)
 * - T take() - Remove and return item from buffer (blocks if empty)
 * - int size() - Get current number of items in buffer
 * 
 * Example:
 * Input: 3 producers each producing 5 items, 2 consumers
 * Output: All 15 items consumed by different consumers, proper blocking when buffer is full
 */

public class Task9 {
    
    static class BoundedBuffer<T> {
        private T[] buffer;
        private int head;
        private int tail;
        private int size;
        private int capacity;
        private Object lock;
        
        @SuppressWarnings("unchecked")
        public BoundedBuffer(int capacity) {
            // TODO: Initialize bounded buffer with given capacity
            //this.buffer = new Object[capacity];
            this.capacity = capacity;
            this.head = 0;
            this.tail = 0;
            this.size = 0;
            this.lock = new Object();
        }

        public void put(T item) throws InterruptedException {
            // TODO: Implement put operation
            // TODO: Block if buffer is full
            // TODO: Add item to buffer
            // TODO: Notify waiting consumers
        }

        public T take() throws InterruptedException {
            // TODO: Implement take operation
            // TODO: Block if buffer is empty
            // TODO: Remove and return item from buffer
            // TODO: Notify waiting producers
            return null;
        }

        public int size() {
            // TODO: Implement size operation
            synchronized(lock) {
                return size;
            }
        }

        public boolean isEmpty() {
            synchronized(lock) {
                return size == 0;
            }
        }

        public boolean isFull() {
            synchronized(lock) {
                return size == capacity;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(5);
        int numProducers = 3;
        int numConsumers = 2;
        int itemsPerProducer = 5;

        Thread[] producers = new Thread[numProducers];
        Thread[] consumers = new Thread[numConsumers];

        // Create producer threads
        for (int i = 0; i < numProducers; i++) {
            final int producerId = i;
            producers[i] = new Thread(() -> {
                for (int j = 0; j < itemsPerProducer; j++) {
                    int item = producerId * 100 + j;
                    try {
                        buffer.put(item);
                        System.out.println("Producer " + producerId + " produced " + item 
                            + ", buffer size: " + buffer.size());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Producer " + producerId + " finished");
            });
        }

        // Create consumer threads
        for (int i = 0; i < numConsumers; i++) {
            final int consumerId = i;
            consumers[i] = new Thread(() -> {
                while (true) {
                    try {
                        Integer item = buffer.take();
                        if (item == -1) { // Sentinel value indicating producer finished
                            buffer.put(-1); // Re-add for other consumers
                            break;
                        }
                        System.out.println("Consumer " + consumerId + " consumed " + item 
                            + ", buffer size: " + buffer.size());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Consumer " + consumerId + " finished");
            });
        }

        // Start all threads
        for (Thread t : producers) {
            t.start();
        }
        for (Thread t : consumers) {
            t.start();
        }

        // Wait for all threads to complete
        for (Thread t : producers) {
            t.join();
        }
        for (Thread t : consumers) {
            t.join();
        }

        System.out.println("All producers and consumers finished!");
    }
}

