/*
Data: 15/07/19
Nome: Cláudia L P Sampedro
Descrição: Escreva uma monitor Counter que possibilita um processodormir at ́e o contador alcan ̧car um valor.  A classe Counterpermite duas opera ̧c ̃oes:increment()esleepUntil(int x).
*/

public class CounterMonitor {

    int counter = 0;

    /**
     * @return the counter
     */
    public int getCounter() {
        return counter;
    }

    public synchronized void increment() {
        counter++;
        notifyAll();
    }

    public synchronized void sleepUntil(int value) {
        while (value > counter) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        CounterMonitor counter = new CounterMonitor();

        Thread sleep = new SleepThread(counter);
        Thread increment = new IncrementThread(counter);

        increment.start();
        sleep.start();

    }
}

class IncrementThread extends Thread {

    CounterMonitor counter;

    IncrementThread(CounterMonitor counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        while (true) {
            counter.increment();
            System.out.println("Incrementando" + counter.getCounter());
        }
    }

}

class SleepThread extends Thread {

    CounterMonitor counter;

    SleepThread(CounterMonitor counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.sleepUntil(5);
        System.out.println("Acordar Thread");
    }

}