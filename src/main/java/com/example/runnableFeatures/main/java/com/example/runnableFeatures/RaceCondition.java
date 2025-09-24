import java.util.concurrent.atomic.AtomicInteger;

public class RaceCondition {

    // Exemple 1 : compteur normal (non sécurisé)
    static class Counter {
        int count = 0;

        void increment() {
            count++; //  Pas atomique → Race condition possible
        }
    }

    // Exemple 2 : compteur avec synchronized
    static class SyncCounter {
        int count = 0;

        synchronized void increment() {
            count++; 
        }
    }

    //  compteur avec AtomicInteger
    static class AtomicCounter {
        AtomicInteger count = new AtomicInteger(0);

        void increment() {
            count.incrementAndGet(); //  Opération atomique
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int iterations = 100000;

        // 1️ Race condition
        Counter counter = new Counter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < iterations; i++) counter.increment();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < iterations; i++) counter.increment();
        });
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println("Race Condition result = " + counter.count); 
        // Résultat attendu = 200000 mais souvent moins

        // withsynchronized
        SyncCounter syncCounter = new SyncCounter();
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < iterations; i++) syncCounter.increment();
        });
        Thread t4 = new Thread(() -> {
            for (int i = 0; i < iterations; i++) syncCounter.increment();
        });
        t3.start(); t4.start();
        t3.join(); t4.join();
        System.out.println("Synchronized result = " + syncCounter.count); 
        // result = 200000

        // with AtomicInteger
        AtomicCounter atomicCounter = new AtomicCounter();
        Thread t5 = new Thread(() -> {
            for (int i = 0; i < iterations; i++) atomicCounter.increment();
        });
        Thread t6 = new Thread(() -> {
            for (int i = 0; i < iterations; i++) atomicCounter.increment();
        });
        t5.start(); t6.start();
        t5.join(); t6.join();
        System.out.println("AtomicInteger result = " + atomicCounter.count); 
        
    }
}
