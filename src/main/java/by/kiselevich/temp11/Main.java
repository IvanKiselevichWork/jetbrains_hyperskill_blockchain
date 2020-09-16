package by.kiselevich.temp11;

public class Main {

    public static void main(String[] args) {
        Thread thread;
        for (int i = 0; i < 3; i++) {
            thread = new Thread(new RunnableWorker());
            thread.setName("worker-" + i);
            thread.start();
        }
    }
}

// Don't change the code below
class RunnableWorker implements Runnable {

    @Override
    public void run() {
        final String name = Thread.currentThread().getName();

        if (name.startsWith("worker-")) {
            System.out.println("too hard calculations...");
        } else {
            return;
        }
    }
}