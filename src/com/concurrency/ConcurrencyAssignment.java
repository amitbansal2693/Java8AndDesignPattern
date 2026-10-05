package com.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Concurrency Assignment: Seat Booking Simulation
 * Problem Statement:
 * - A movie hall has a limited number of seats.
 * - Multiple customers try to book seats at the same time.
 * - The booking logic must be thread-safe so that two customers do not book
 *   the same seat.
 * Concepts demonstrated:
 * 1. Shared mutable state
 * 2. synchronized method
 * 3. Thread class usage
 * 4. ExecutorService usage
 * 5. Race-condition prevention
 * Run:
 *   java -cp src com.concurrency.ConcurrencyAssignment
 */
public class ConcurrencyAssignment {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Concurrency Assignment: Seat Booking Simulation ===");
        System.out.println("Goal: book 5 seats using multiple threads safely.\n");

        BookingCounter bookingCounter = new BookingCounter(5);

        // Direct Thread example
        Thread t1 = new Thread(new BookingTask(bookingCounter, "Alice"), "Thread-Alice");
        Thread t2 = new Thread(new BookingTask(bookingCounter, "Bob"), "Thread-Bob");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("\n--- ExecutorService example ---");
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<String> customers = List.of("Charlie", "Diana", "Ethan", "Fiona", "George", "Hannah");

        for (String customer : customers) {
            executorService.submit(new BookingTask(bookingCounter, customer));
        }

        executorService.shutdown();
        boolean finished = executorService.awaitTermination(5, TimeUnit.SECONDS);
        if (!finished) {
            System.out.println("Executor did not finish in time.");
        }

        System.out.println("\n=== Final Booking Summary ===");
        bookingCounter.printSummary();
    }

    static class BookingCounter {
        private int availableSeats;
        private final List<String> bookedBy = new ArrayList<>();

        BookingCounter(int totalSeats) {
            this.availableSeats = totalSeats;
        }

        public synchronized void bookSeat(String customerName) {
            if (availableSeats <= 0) {
                System.out.println(Thread.currentThread().getName()
                        + " -> Sorry " + customerName + ", no seats left.");
                return;
            }

            availableSeats--;
            bookedBy.add(customerName);
            System.out.println(Thread.currentThread().getName()
                    + " -> Seat booked for " + customerName
                    + " | remaining seats: " + availableSeats);
        }

        public synchronized void printSummary() {
            System.out.println("Booked customers: " + bookedBy);
            System.out.println("Seats still available: " + availableSeats);
        }
    }

    static class BookingTask implements Runnable {
        private final BookingCounter bookingCounter;
        private final String customerName;

        BookingTask(BookingCounter bookingCounter, String customerName) {
            this.bookingCounter = bookingCounter;
            this.customerName = customerName;
        }

        @Override
        public void run() {
            bookingCounter.bookSeat(customerName);
        }
    }
}

