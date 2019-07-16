/*
Data: 15/07/19
Nome: Cláudia L P Sampedro
Descrição: Escreva um monitor BoundedCounter que possui um valorm ́ınimo e m ́aximo.  A classe possui dois m ́etodos:increment()edecrement().  Ao alcan ̧car os limites m ́ınimo ou m ́aximo, athread que alcan ̧cou deve ser bloqueada.
*/

public class BoundedMonitor {

    int counter = 0;
    int minValue = -2;
    int maxValue = 5;

    /**
     * @return the counter
     */
    public synchronized int getCounter() {
        return counter;
    }

    public synchronized void increment() {

        if (counter < maxValue) {
            counter++;
        }

        while (counter == maxValue) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
    }

    public synchronized void decrement() {
        if (counter > minValue) {
            counter--;
        }

        while (counter == minValue) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) {
        new Thread() {
            BoundedMonitor monitor = new BoundedMonitor();

            @Override
            public void run() {
                while (true) {
                    monitor.increment();
                    System.out.println(monitor.getCounter() + "Incrementando");
                }
            }
        }.start();

        new Thread() {
            BoundedMonitor monitor = new BoundedMonitor();

            @Override
            public void run() {
                while (true) {
                    monitor.decrement();
                    System.out.println(monitor.getCounter() + "Decrementando");
                }
            }
        }.start();
    }
}
