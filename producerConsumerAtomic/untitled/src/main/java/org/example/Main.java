package org.example;

import org.example.Producer;
import org.example.SharedBuffer;

public class Main {
    public static void main(String[] args) {
        int bufferSize = 10;
        int itemCount = 20; // Number of items per producer/consumer

        SharedBuffer buffer = new SharedBuffer(bufferSize);

        Producer p1 = new Producer(buffer, itemCount, "Producer-1");
        Producer p2 = new Producer(buffer, itemCount, "Producer-2");

        Consumer c1 = new Consumer(buffer, itemCount, "Consumer-1");
        Consumer c2 = new Consumer(buffer, itemCount, "Consumer-2");


        // Start all threads
        p1.start();
        p2.start();
        c1.start();
        c2.start();

        try {
            p1.join();
            p2.join();
            c1.join();
            c2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All threads finished.");
    }
}
