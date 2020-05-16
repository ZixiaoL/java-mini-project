package edu.nyu.cs9053.homework9;

import java.util.concurrent.Semaphore;

/**
 * User: blangel
 */
public class Factory {

    private static final Semaphore BINARY_SEMAPHORE = new Semaphore(1);

    public static Customer createCustomer() {
        Customer customer = new ConcreteCustomer(BINARY_SEMAPHORE);
        return customer;
    }

    public static Barista createBarista() {
        Barista barista = new ConcreteBarista(BINARY_SEMAPHORE);
        return barista;
    }
}
