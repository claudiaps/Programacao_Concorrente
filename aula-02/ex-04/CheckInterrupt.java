public class CheckInterrupt extends Thread {

    Thread[] thread;

    CheckInterrupt(Thread[] thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            if (thread[i].isInterrupted()) {
                System.out.println("Thread " + i + " is interrupted");
            }
        }
    }

    public static void main(String[] args) {
        Thread[] threadsArray = new Thread[3];
        for (int i = 0; i < 3; i++) {
            threadsArray[i] = new RegularThread();
            threadsArray[i].start();
        }
        threadsArray[2].interrupt();
        Thread threadMonitor = new CheckInterrupt(threadsArray);
        threadMonitor.start();

    }
}

class RegularThread extends Thread {
    @Override
    public void run() {
        System.out.println("starting a new thread");
    }
}