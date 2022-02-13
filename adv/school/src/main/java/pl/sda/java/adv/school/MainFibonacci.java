package pl.sda.java.adv.school;

import java.util.stream.StreamSupport;

public class MainFibonacci {
    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();

        int counter = 0;
        for(Long value : fib) {
            counter++;
            System.out.println(value);
            if (counter >= 15) {
                break;
            }
        }
        StreamSupport.stream(fib.spliterator(), false)
                .skip(5)
                .limit(15)
                .forEach(System.out::println);


    }
}
