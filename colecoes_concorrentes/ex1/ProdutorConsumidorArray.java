
/* 
Decrição:  Implemente duas vers ̃oes do problema do produtor/consumidor com Mprodutores e N consumidores usandoArrayBlockingQueueeLinkedBlockingQueue.  Compare o desempenho das duasimplementa ̧c ̃oes.
Aluna: Cláudia Sampedro
Data: 24/06/19
*/

import java.util.concurrent.ArrayBlockingQueue;

public class ProdutorConsumidorArray {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> array = new ArrayBlockingQueue<Integer>(1000);
        Producer p = new Producer(array);
        Consumer c1 = new Consumer(array);
        Consumer c2 = new Consumer(array);
        
        long start = System.currentTimeMillis();
        new Thread(p).start();
        new Thread(c1).start();
        new Thread(c2).start();
        long finish = System.currentTimeMillis();

        long total = finish - start;
        System.out.println("Tempo total: " + total);
    }
}

class Producer extends Thread {
    private final ArrayBlockingQueue<Integer> arrayQueue;
    int value = 0;
    int cont = 1000;

    Producer(ArrayBlockingQueue<Integer> arrayQueue) {
        this.arrayQueue = arrayQueue;
    }

    @Override
    public void run() {
        try {
            while (cont >= 0) {
                arrayQueue.put(produce());
                cont -= 1;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    int produce() {
        value += 1;
        return value;
    }

}

class Consumer extends Thread {
    private final ArrayBlockingQueue<Integer> arrayQueue;
    int cont = 1000;

    Consumer(ArrayBlockingQueue<Integer> arrayQueue) {
        this.arrayQueue = arrayQueue;
    }

    public void run() {
        try {
            while (cont >= 0) {
                consume(arrayQueue.take());
                cont -= 1;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    void consume(int x) {
        System.out.println(x);
    }

}