package com.test.oa;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class OaApplication {

    public static void main(String[] args) throws InterruptedException {
        int numberOfCars = 5;

        CountDownLatch startSignal = new CountDownLatch(1);
        ExecutorService executor = Executors.newFixedThreadPool(numberOfCars);
        List<CompletableFuture<Void>> raceTasks = new ArrayList<>();

        for (int carId = 1; carId <= numberOfCars; carId++) {
            final int currentCarId = carId;

            CompletableFuture<Void> task = CompletableFuture.runAsync(() -> {
                try {
                    System.out.println("Car #" + currentCarId + " is ready.");
                    startSignal.await(); // Wait for the race to start

                    long startTime = System.currentTimeMillis();
                    System.out.println("Car #" + currentCarId + " started racing.");

                    Thread.sleep((long) (Math.random() * 3000 + 1000)); // Simulate race duration

                    long endTime = System.currentTimeMillis();
                    long duration = endTime - startTime;

                    System.out.printf("Car #%d finished in %d ms.%n", currentCarId, duration);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, executor);

            raceTasks.add(task);
        }

        // Start the race
        System.out.println("All cars are at the starting line...");
        Thread.sleep(2000); // "Ready... Set... Go!"
        System.out.println("--------- GO! ---------");
        startSignal.countDown();

        // Wait for all cars to finish
        CompletableFuture.allOf(raceTasks.toArray(new CompletableFuture[0])).join();

        executor.shutdown();
    }

}

