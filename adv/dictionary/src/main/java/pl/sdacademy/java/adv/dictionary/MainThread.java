package pl.sdacademy.java.adv.dictionary;

public class MainThread {
    public static void main(String[] args) throws InterruptedException{
        System.out.println("Starting...");

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("Custom after 2000");
                } catch (InterruptedException e) {
                    System.out.println("Custom thread got interrupted!");
                }
            }
        };
        t.start();

        Thread.sleep(1000);
        System.out.println("Main after 1000");

        t.interrupt();

        Thread.sleep(2000);
        System.out.println("Main after 3000");
    }
}
