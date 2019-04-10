import java.util.*;


class Counter{
    int last_number;
    int value = 1;

    public Counter(int last_number){
        this.last_number = last_number;
    }

    public int getValue() {
        if(value > last_number){
            return -1;
        }
        // System.out.println(value);
        return value++;

    }
}

public class GetPrimo extends Thread{
    
    Counter counter;
    ArrayList <Integer> primo_array;

    GetPrimo(Counter counter, ArrayList <Integer> primo_array){
        this.counter = counter;
        this.primo_array = primo_array;
    }
    
    @Override
    public void run() {
        while(true){
            boolean isPrimo = true;
            int number = counter.getValue();
            // System.out.println(number);
            if(number == -1){
                break;
            }
            for(int i = 2; i< number; i++){
                if(number%i == 0){
                    isPrimo = false;
                }
            }
            if(isPrimo){
                primo_array.add(number);
            }
        }
        
    }

    public static void main(String[] args) {
        Counter cont = new Counter(Integer.parseInt(args[0]));
        ArrayList <Integer> primos = new ArrayList<>();
        Thread t1 = new GetPrimo(cont, primos);
        Thread t2 = new GetPrimo(cont, primos);
        Thread t3 = new GetPrimo(cont, primos);
        Thread t4 = new GetPrimo(cont, primos);
        Thread t5 = new GetPrimo(cont, primos);
        Thread t6 = new GetPrimo(cont, primos);
        Thread t7 = new GetPrimo(cont, primos);
        Thread t8 = new GetPrimo(cont, primos);


        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();


        System.out.println(primos);
        
    }


} 