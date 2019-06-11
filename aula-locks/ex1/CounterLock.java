/*
Atividades da aula de 03/06
Nome: Cláudia L P Sampedro
Descrição: Faca um programa usando Lock para simular a atualiza ̧c ̃ao deum contador que  ́e acessado por m ́ultiplas threads.  Ocontador pode diminuir e aumentar.
*/


import java.util.concurrent.locks.ReentrantLock;

class Counter {

    int value = 0;

    int addValue() {
        return value++;
    }

    int subValue() {
        return value--;
    }

}

public class CounterLock extends Thread {

    ReentrantLock lock;
    Counter value;
    int op;

    public CounterLock(ReentrantLock lock, Counter value, int op) {
        this.lock = lock;
        this.value = value;
        this.op = op;
    }

    @Override
    public void run() {
        lock.lock();
        if (op == 1) {
            value.addValue();
            System.out.println(value.value);
        } else {
            value.subValue();
            System.out.println(value.value);
        }
        lock.unlock();
    }

    public static void main(String[] args) {

        Counter value = new Counter();
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new CounterLock(lock, value, 1);
        Thread t2 = new CounterLock(lock, value, 0);
        Thread t3 = new CounterLock(lock, value, 1);
        Thread t4 = new CounterLock(lock, value, 1);
        Thread t5 = new CounterLock(lock, value, 0);


        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }
}