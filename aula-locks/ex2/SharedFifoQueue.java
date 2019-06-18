/*
Aluna: Cláudia L P Sampedro
Data: 18/06
Descrição: Crie uma classe SharedFifoQueue e use Conditions paracontrolar se a fila est ́a vazia ou cheia.  Teste usando threadsprodutoras e consumidoras.
*/


import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class Producer extends Thread{
    SharedFifoQueue queue;
    int value;

    Producer(SharedFifoQueue queue, int value){
        this.queue = queue;
        this.value = value;
    }

    @Override
    public void run() {
        try {
            queue.put(value);
            System.out.println("Inserindo o valor: " + value);
        } catch(Exception e ){
            e.printStackTrace();
        }
    }

}

class Consumer extends Thread{
    SharedFifoQueue queue;

    Consumer(SharedFifoQueue queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            queue.take();
            System.out.println("Removendo o primeiro valor");
        } catch(Exception e ){
            e.printStackTrace();
        }
    }

}

public class SharedFifoQueue{
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final ArrayList<Integer> array = new ArrayList<Integer>();
    int count = 0;

    public void put(int value) throws InterruptedException {
        lock.lock();
        try {
            while(count == 10){
                notFull.await();
            }
            array.add(value);
            count = count + 1;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public void take() throws InterruptedException {
        lock.lock();
        try {
            while(count == 0){
                notEmpty.await();
            }
            array.remove(0);
            count = count -1;
            notFull.signal();
        } finally{
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SharedFifoQueue queue = new SharedFifoQueue();
        Thread consumer = new Consumer(queue);
        Thread consumer2 = new Consumer(queue);

        Thread producer = new Producer(queue, 10);
        Thread producer2 = new Producer(queue, 11);

        producer.start();
        consumer.start();
        producer2.start();
        consumer2.start();
    }   

}