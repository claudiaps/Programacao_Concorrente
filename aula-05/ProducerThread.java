/*
Atividades da aula de 16/04
Nome: Cláudia L P Sampedro
Descrição: Implemente o problema do produtor-consumidor que h ́a umbuffer compartilhado entre threads.  H ́a uma  ́unica threadprodutora e uma  ́unica consumidora.  O buffer  ́e preenchidoem tempos aleat ́orios pela thread produtora.  Assim que forproduzido algo, a thread consumidora deve ser comunicadapara obter o valor
*/

import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;

public class ProducerThread extends Thread {

    WaitNotify monitor;
    AtomicInteger buffer;

    ProducerThread(WaitNotify monitor, AtomicInteger buffer){
        this.monitor = monitor;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();            
            }
            if(buffer.get() == -1){
                Random rand = new Random();
                int value = rand.nextInt(100) + 1;
                System.out.println("Producer");
                buffer.set(value);
                monitor.notifying();
            }
        }
    }

    public static void main(String[] args) {
        WaitNotify monitor = new WaitNotify();
        AtomicInteger buffer = new AtomicInteger(-1);
        Thread producer = new ProducerThread(monitor, buffer);
        Thread consumer = new ConsumerThread(monitor, buffer);
        producer.start();
        consumer.start();

    }
}

class ConsumerThread extends Thread {

    WaitNotify monitor;
    AtomicInteger buffer;

    ConsumerThread(WaitNotify monitor, AtomicInteger buffer){
        this.monitor = monitor;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true){
            monitor.waiting();
            System.out.println("consumer" + buffer.get());
            buffer.set(-1);
        }
    }

}

class WaitNotify {

    Object monitor = new Object();
    boolean signal = false;

    public void waiting() {
        synchronized (monitor) {
            if (!signal) {
                try {
                    monitor.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            signal = false;
        }

    }

    public void notifying(){
        synchronized(monitor){
            signal = true;
            monitor.notify();
        }
    }

}
