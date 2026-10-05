package com.concurrency.concurrency.hard;

import java.util.*;

/**
 * Task 10: Read-Write Lock (Hard)
 * 
 * Implement a reader-writer lock that allows:
 * 
 * 1. Multiple readers can hold the lock simultaneously
 * 2. Only one writer can hold the lock at a time
 * 3. A writer has exclusive access (no readers or other writers)
 * 4. Readers acquire read lock via readLock()
 * 5. Writers acquire write lock via writeLock()
 * 6. Each lock supports lock() and unlock() operations
 * 
 * Design a ReadWriteLock class that supports:
 * - Lock readLock() - Returns a read lock object with lock() and unlock() methods
 * - Lock writeLock() - Returns a write lock object with lock() and unlock() methods
 * 
 * Example:
 * Input: 5 reader threads, 1 writer thread
 * Output: Multiple readers can read simultaneously, writer waits for readers and has exclusive access
 * 
 * This is a classic synchronization problem that optimizes for scenarios where reads are more frequent than writes.
 */

public class Task10 {
    
    static class ReadWriteLock {
        private int readers = 0;
        private int writers = 0;
        private int waitingWriters = 0;
        private Object lock = new Object();

        class ReadLock implements Lock {
            public void lock() throws InterruptedException {
                // TODO: Implement read lock acquisition
                // TODO: Wait if writers are present or waiting
                // TODO: Increment reader count
            }

            public void unlock() {
                // TODO: Implement read lock release
                // TODO: Decrement reader count
                // TODO: Notify waiting writers if no more readers
            }
        }

        class WriteLock implements Lock {
            public void lock() throws InterruptedException {
                // TODO: Implement write lock acquisition
                // TODO: Wait if readers or writers are present
                // TODO: Set writer flag
            }

            public void unlock() {
                // TODO: Implement write lock release
                // TODO: Unset writer flag
                // TODO: Notify waiting readers and writers
            }
        }

        private ReadLock readLock = new ReadLock();
        private WriteLock writeLock = new WriteLock();

        public Lock readLock() {
            return readLock;
        }

        public Lock writeLock() {
            return writeLock;
        }
    }

    interface Lock {
        void lock() throws InterruptedException;
        void unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLock rwLock = new ReadWriteLock();
        List<String> data = new ArrayList<>();
        data.add("Initial");

        int numReaders = 5;
        int numWriters = 1;

        Thread[] readers = new Thread[numReaders];
        Thread[] writers = new Thread[numWriters];

        // Create reader threads
        for (int i = 0; i < numReaders; i++) {
            final int readerId = i;
            readers[i] = new Thread(() -> {
                for (int iter = 0; iter < 3; iter++) {
                    try {
                        rwLock.readLock().lock();
                        System.out.println("Reader " + readerId + " reading: " + data);
                        Thread.sleep(100); // Simulate reading
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        rwLock.readLock().unlock();
                    }
                }
                System.out.println("Reader " + readerId + " finished");
            });
        }

        // Create writer threads
        for (int i = 0; i < numWriters; i++) {
            final int writerId = i;
            writers[i] = new Thread(() -> {
                for (int iter = 0; iter < 2; iter++) {
                    try {
                        rwLock.writeLock().lock();
                        data.clear();
                        data.add("Written by writer " + writerId + " at " + System.currentTimeMillis());
                        System.out.println("Writer " + writerId + " writing: " + data);
                        Thread.sleep(200); // Simulate writing
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        rwLock.writeLock().unlock();
                    }
                }
                System.out.println("Writer " + writerId + " finished");
            });
        }

        // Start all threads
        for (Thread t : readers) {
            t.start();
        }
        for (Thread t : writers) {
            t.start();
        }

        // Wait for all threads to complete
        for (Thread t : readers) {
            t.join();
        }
        for (Thread t : writers) {
            t.join();
        }

        System.out.println("All readers and writers finished!");
    }
}

