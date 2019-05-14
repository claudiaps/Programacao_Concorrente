import java.util.concurrent.Semaphore;

/*
Nome: Cláudia L P Sampedro
Data: 30/04
Descrição: Enviar sinalização para um ponto de encontro entre threads.Faça um código que uma thread t1 e t2 são inicializadas e t1 espera por t2 e vice-versa.
*/

public class Rendezvous extends Thread {

    Semaphore semaforo1;
    Semaphore semaforo2;

    Rendezvous(Semaphore semaforo1, Semaphore semaforo2) {
        this.semaforo1 = semaforo1;
        this.semaforo2 = semaforo2;
    }

    @Override
    public void run() {
        try {
            System.out.println("Parte 1.1");
            Thread.sleep(100);
            semaforo1.release();
            semaforo2.acquire();
            System.out.println("Parte 1.2");
            Thread.sleep(100);
            semaforo2.release();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void main(String[] args) {
        Semaphore semaforo1 = new Semaphore(0);
        Semaphore semaforo2 = new Semaphore(0);
        Thread t1 = new Rendezvous(semaforo1, semaforo2);
        Thread t2 = new Rendezvous2(semaforo1, semaforo2);
        t1.start();
        t2.start();

    }
}

class Rendezvous2 extends Thread {

    Semaphore semaforo1;
    Semaphore semaforo2;

    Rendezvous2(Semaphore semaforo1, Semaphore semaforo2) {
        this.semaforo1 = semaforo1;
        this.semaforo2 = semaforo2;
    }

    @Override
    public void run() {
        try {
            System.out.println("Parte 2.1");
            Thread.sleep(100);
            semaforo2.release();
            semaforo1.acquire();
            System.out.println("Parte 2.2");
            Thread.sleep(100);
            semaforo2.release();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}