/*
Atividades da aula de 09/04
Nome: Cláudia L P Sampedro
Descrição: Para o exerc ́ıcio anterior, compare o desempenho medindo otempo de in ́ıcio e t ́ermino para processar o int
*/

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.time.*;


class Counter{
    int last_number;
    private final AtomicInteger value = new AtomicInteger(1);

    public Counter(int last_number){
        this.last_number = last_number;
    }

    public int getValue() {
        if(value.get() > last_number){
            return -1;
        }
        return value.incrementAndGet();

    }
}

public class GetPrimoAtomic extends Thread{
    
    Counter counter;
    ArrayList <Integer> primo_array;

    GetPrimoAtomic(Counter counter, ArrayList <Integer> primo_array){
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
        Thread t1 = new GetPrimoAtomic(cont, primos);
        Thread t2 = new GetPrimoAtomic(cont, primos);
        Thread t3 = new GetPrimoAtomic(cont, primos);
        Thread t4 = new GetPrimoAtomic(cont, primos);

        LocalTime timer1 = LocalTime.now();
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (Exception e) {
            //TODO: handle exception
        }
        LocalTime timer2 = LocalTime.now();
        
        System.out.println(primos);
        
        System.out.println(timer1);
        System.out.println(timer2);
    }


} 