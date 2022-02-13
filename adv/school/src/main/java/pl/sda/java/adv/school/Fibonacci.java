package pl.sda.java.adv.school;

import java.util.Iterator;

public class Fibonacci implements Iterable<Long>{

    @Override
    public Iterator<Long> iterator() {
        return new FibonacciIterator();
    }

    private static class FibonacciIterator implements Iterator<Long> {
        private long first = 0;
        private long second = 1;

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Long next() {
            long result = first;
            first = second;
            second = result + first;
            return result;
        }
    }
}
