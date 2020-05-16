package edu.nyu.cs9053.homework9;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class ConcreteCustomer implements Customer {

    private final Semaphore binarySemaphore;

    public ConcreteCustomer(Semaphore binarySemaphore) {
        this.binarySemaphore = binarySemaphore;
    }

    @Override public OrderNumber order(Queue queue) {
        if(queue == null) {
            throw new IllegalArgumentException("Queue not exists!");
        }
        try {
            binarySemaphore.acquire();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }

        try {
            if(queue.full()) {
                return null;
            }
            CoffeeDrink coffeeDrink;
            Random random = new Random();
            int flavor = random.nextInt(3);
            switch(flavor) {
                case 0:
                    coffeeDrink = new Espresso();
                    break;
                case 1:
                    coffeeDrink = new DecafEspresso();
                    break;
                case 2:
                    coffeeDrink = new Latte();
                    break;
                default:
                    coffeeDrink = new Espresso();
                    break;
            }
            OrderNumber orderNumber = queue.addOrder(coffeeDrink);
            return orderNumber;
        } finally {
            binarySemaphore.release();
        }
    }

}
