/*
Atividades da aula de 01/04
Nome: Cláudia L P Sampedro
Descrição:  Faça um programa em Java que consulte periodicamente oestado de um conjunto de threads.
*/

public class CheckState extends Thread {

    Thread[] thread;

    CheckState(Thread[] thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        while(true){
            for (int i = 0; i < 3; i++) {
                System.out.println(thread[i].getState().name());
            }
        }
    }

    public static void main(String[] args) {
        Thread[] threadsArray = new Thread[3];
        for (int i = 0; i < 3; i++) {
            threadsArray[i] = new RegularThread();
            threadsArray[i].start();
        }

        Thread threadMonitor = new CheckState(threadsArray);
        threadMonitor.start();

    }
}

class RegularThread extends Thread {
    @Override
    public void run() {
        System.out.println("starting a new thread");
        try {
            Thread.sleep(1000);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}