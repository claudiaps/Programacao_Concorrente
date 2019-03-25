import java.util.ArrayList;

class Counter{
    int value = 0;

    /**
     * @return the value
     */
    public int getValue() {
        return value++;
    }
}

public class UnsafetyThread extends Thread{

    Counter count;
    ArrayList numbers;

     UnsafetyThread(Counter count, ArrayList numbers){
        this.count = count;
        this.numbers = numbers;
    }

    @Override
    public void run() {
        while(true){
            try {
                int i = count.getValue();
                System.out.println(i);
                if (numbers.contains(i)){
                    System.out.println("Inconsistência");
                    System.exit(0);
                }
                this.numbers.add(i);
                // Thread.sleep(1000);
            } catch (Exception e) {

            }
        }
    }

    public static void main(String[] args) {
        Counter x = new Counter();
        ArrayList n = new ArrayList<Integer>();
        for (int i=0; i<100; i++){
            new UnsafetyThread(x,n).start();
        }
    }
}