import java.util.concurrent.Semaphore;

/*
Nome: Cláudia L P Sampedro
Data: 30/04
Descrição: Enviar sinal para outra thread para indicar que um evento ocorreu.Faça um código que uma thread t1 e t2 são inicializadas simultaneamente, mas a t2 pode somente continuar a execução após a sinalização de t1.
*/

public class Signaling extends Thread {
    Semaphore semaforo;
    int value;

    Signaling(Semaphore semaforo, int value) {
        this.semaforo = semaforo;
        this.value = value;
    }

    @Override
    public void run() {
        try {
            value++;
            System.out.println("new value: " + value);
            Thread.sleep(100);
        } catch (Exception e) {
            //TODO: handle exception
        }
        semaforo.release();
    }

    public static void main(String[] args) {
        Semaphore semaforo = new Semaphore(0);
        int value = 0;
        Thread t1 = new Signaling(semaforo, value);
        Thread t2 = new Thread2(semaforo, value);
        t1.start();
        t2.start();
    }
}

class Thread2 extends Thread {

    Semaphore semaforo;
    int value;

    Thread2(Semaphore semaforo, int value) {
        this.semaforo = semaforo;
        this.value = value;
    }

    @Override
    public void run() {
        try {
            semaforo.acquire();
            value++;
            System.out.println("New value2: " + value);
            Thread.sleep(100);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}