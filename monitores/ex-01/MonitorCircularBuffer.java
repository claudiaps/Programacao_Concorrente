import java.util.Random;

/*
Atividades da aula de 22/04
Nome: Cláudia L P Sampedro
Descrição: Implemente uma solucao com monitor para o problema do Produtor-Consumidor usando um buffer circular.
*/

public class MonitorCircularBuffer {
    int[] buffer = new int[8];
    Object objMonitor = new Object();
    int position_take = 0;
    int position_put = 0;
    boolean empty = true;
    boolean full = false;

    public int get() {
        while(empty){
            try {
                objMonitor.wait();
            } catch (Exception e) {
                //TODO: handle exception
            }
            
        }
        return 1;
    }
    public void put() {
        //notify
    }

}

class Producer extends Thread {
    // Random rand = new Random();
    // int value = rand.nextInt(100);
    @Override
    public void run() {

    }

}

class Consumer extends Thread {

    @Override
    public void run() {
    }

}
