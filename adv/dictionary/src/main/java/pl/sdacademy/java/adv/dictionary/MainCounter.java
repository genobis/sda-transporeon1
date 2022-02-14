package pl.sdacademy.java.adv.dictionary;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

public class MainCounter {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final int threads = 20;
        final ExecutorService executorService = Executors.newFixedThreadPool(threads);
        //final CounterState counterState = new UnsafeCounterState();
        //final CounterState counterState = new SynchronizedCounterState();
        final CounterState counterState = new AtomicCounterState();

        List<Future<?>> futures = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            futures.add(executorService.submit(counterState::increment));
        }

        for (Future<?> future : futures) {
            future.get();
        }

        System.out.println(counterState.getValue());
        executorService.shutdown();
    }

    private interface CounterState {
        long getValue();

        void increment();
    }

    private static class SynchronizedCounterState implements CounterState {
        private long counter;

        @Override
        public long getValue() {
            return counter;
        }

        @Override
        public void increment() {
            // global constant as monitor object - synchronizes access to all instances
            //we usually do NOT want that
            /*synchronized ("xyz") {
                counter = counter + 1;
            }*/
            synchronized (this) {
                counter = counter + 1;
            }
        }
    }

    private static class AtomicCounterState implements CounterState {
        private final AtomicLong atomicLong = new AtomicLong();

        @Override
        public long getValue() {
            return atomicLong.longValue();
        }

        @Override
        public void increment() {
            atomicLong.getAndIncrement();
        }
    }
}
