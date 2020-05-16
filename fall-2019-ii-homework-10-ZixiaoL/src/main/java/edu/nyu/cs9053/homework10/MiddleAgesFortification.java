package edu.nyu.cs9053.homework10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: blangel
 */
public class MiddleAgesFortification extends AbstractConcurrencyFactorProvider implements Fortification<Thread> {

    private final List<Thread> threads;
    private final AtomicInteger running;

    public MiddleAgesFortification(int concurrencyFactor) {
        super(concurrencyFactor);
        this.threads = new ArrayList<>(concurrencyFactor);
        this.running = new AtomicInteger(0);
        for(int i = 0; i < concurrencyFactor; i++) {
            threads.add(new Thread());
        }
    }

    @Override public void handleAttack(AttackHandler handler) {
        if(handler == null) {
            throw new IllegalArgumentException();
        }
        if(running.get() < getConcurrencyFactor()) {
            for(Thread thread : threads) {
                if(!thread.isAlive()) {
                    running.incrementAndGet();
                    thread = new Thread(new Runnable() {
                        @Override public void run() {
                            handler.soldiersReady();
                            running.decrementAndGet();
                        }
                    });
                    thread.start();
                    break;
                }
            }
        }
    }

    @Override public void surrender() {
        for(Thread thread : threads) {
                thread.interrupt();
        }
    }

}
