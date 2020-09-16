package by.kiselevich.temp13;

import java.util.Scanner;
import java.util.Arrays;
import java.util.concurrent.*;

public class Main {

    private static long mainThreadId = Thread.currentThread().getId();

    // Fix this method
    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        SlowStringProcessor processor = new SlowStringProcessor(str);
        processor.start();
        processor.join();

        System.out.println(processor.getNumberOfUniqueCharacters());
    }

    //Don't change the code below
    static class SlowStringProcessor extends Thread {

        private final String s;
        private volatile long numberOfUniqueCharacters = 0;

        public SlowStringProcessor(String s) {
            this.s = s;
        }

        public synchronized void f() {

        }

        @Override
        public void run() {

            final long currentId = Thread.currentThread().getId();

            if (currentId == mainThreadId) {
                throw new RuntimeException("You must start a new thread!");
            }

            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                throw new RuntimeException("Do not interrupt the processor");
            }

            this.numberOfUniqueCharacters = Arrays.stream(s.split("")).distinct().count();
        }

        public long getNumberOfUniqueCharacters() {
            return numberOfUniqueCharacters;
        }
    }
}

class Account {

    private long balance = 0;

    public synchronized boolean withdraw(long amount) {
        if (balance < amount) {
            return false;
        }
        balance -= amount;
        return true;
    }

    public synchronized void deposit(long amount) {
        balance += amount;
    }

    public synchronized long getBalance() {
        return balance;
    }
}

// -=----------------------------- NEW TASK

/* Do not change this class */
class Message {
    final String text;
    final String from;
    final String to;

    Message(String from, String to, String text) {
        this.text = text;
        this.from = from;
        this.to = to;
    }
}

/* Do not change this interface */
interface AsyncMessageSender {
    void sendMessages(Message[] messages);
    void stop();
}

class AsyncMessageSenderImpl implements AsyncMessageSender {
    private ExecutorService executor; // TODO initialize the executor
    private final int repeatFactor;

    public AsyncMessageSenderImpl(int repeatFactor) {
        this.repeatFactor = repeatFactor;
    }

    @Override
    public void sendMessages(Message[] messages) {
        for (Message msg : messages) {
            // TODO repeat messages
            executor.submit(() -> {
                System.out.printf("(%s>%s): %s\n", msg.from, msg.to, msg.text); // do not change it
            });
        }
    }

    @Override
    public void stop() {
        // TODO stop the executor and wait for it
    }
}