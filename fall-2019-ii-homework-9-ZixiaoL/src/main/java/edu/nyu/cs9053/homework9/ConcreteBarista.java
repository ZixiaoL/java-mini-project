package edu.nyu.cs9053.homework9;

import java.util.concurrent.Semaphore;

public class ConcreteBarista implements Barista {

    private final Semaphore binarySemaphore;

    public ConcreteBarista(Semaphore binarySemaphore) {
        this.binarySemaphore = binarySemaphore;
    }

    @Override public OrderNumber handle(Queue from) {
        if(from == null) {
            throw new IllegalArgumentException("Queue not exists!");
        }
        try {
            binarySemaphore.acquire();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }

        try {
            if(from.isEmpty()) {
                return null;
            }
            OrderNumber orderNumber = from.getOrderNumber();
            return orderNumber;
        } finally {
            binarySemaphore.release();
        }
    }

}
