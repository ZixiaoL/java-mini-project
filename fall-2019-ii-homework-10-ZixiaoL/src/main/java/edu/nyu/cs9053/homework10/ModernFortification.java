package edu.nyu.cs9053.homework10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * User: blangel
 */
public class ModernFortification extends AbstractConcurrencyFactorProvider implements Fortification<ExecutorService> {

    private final ExecutorService executorService;

    public ModernFortification(int concurrencyFactor) {
        super(concurrencyFactor);
        this.executorService = Executors.newFixedThreadPool(concurrencyFactor);
    }

    @Override public void handleAttack(AttackHandler handler) {
        if(handler == null) {
            throw new IllegalArgumentException();
        }
        final Runnable getReady = new Runnable() {
            @Override public void run() {
                handler.soldiersReady();
            }
        };
        executorService.submit(getReady);
    }

    @Override public void surrender() {
        executorService.shutdown();
    }

}
