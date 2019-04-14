/*
Atividades da aula de 01/04
Nome: Cláudia L P Sampedro
Descrição: Faca um programa em Java com threads que exiba osn ́umeros primos entre 0 e 100000.
*/

import java.util.*;

class Counter{
    int last_number;
    int value = 0;

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

public class GetPrimos extends Thread{
    
    Counter counter;
    ArrayList <Integer> primo_array;

    GetPrimos(Counter counter, ArrayList <Integer> primo_array){
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
        Counter cont = new Counter(100000);
        ArrayList <Integer> primos = new ArrayList<>();
        Thread t1 = new GetPrimos(cont, primos);
        Thread t2 = new GetPrimos(cont, primos);
        Thread t3 = new GetPrimos(cont, primos);
        Thread t4 = new GetPrimos(cont, primos);

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

        for (int i = 0; i< primos.size(); i++){
            System.out.println(primos.get(i));
        }
        
    }


} 