/*
Atividades da aula de 25/03
Nome: Cláudia L P Sampedro
Descrição: Fa ca um programa que receba um valor indicando um n ́umerode threads a serem instanciadas e teste os dois modos de criar threads em Java.* dica:  use oThread.sleeppara pausar as threads por umintervalo de tempo.
*/



public class ThreadsRunnable implements Runnable{

    @Override
    public void run(){
        System.out.println("Hello from a thread with runnable");
    }

    public static void main(String[] args) {
        int nThreads = Integer.parseInt(args[0]);

        for (int i =0; i< nThreads; i++){
            new Thread(new ThreadsRunnable()).start();
        }
    }

}