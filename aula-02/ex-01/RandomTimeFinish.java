import java.util.Random;

public class RandomTimeFinish extends Thread {

    @Override
    public void run() {
        System.out.println("Hello from a new thread");
        try {
            Random rand = new Random();
            int value = rand.nextInt(100) + 1;
            Thread.sleep(value);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new RandomTimeFinish().start();
        }
    }

}