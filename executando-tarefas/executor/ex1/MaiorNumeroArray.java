/*
Descrição: Fa ̧ca um programa que localize o maior valor em um vetor.  Divida oprograma em tarefas que localizam o maior valor em um segmento dovetor.  O programa deve possibilitar especificar o n ́umero de tarefas e on ́umero de threads para resolver o problema.
Aluna: Cláudia Sampedro
Data: 15/07/19
*/



import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.List;
import java.util.concurrent.Future;

public class MaiorNumeroArray implements Callable<Void> {

    int inicio;
    int fim;
    int[] array;
    AtomicInteger maior;
    static Lock lock = new ReentrantLock();

    MaiorNumeroArray(int inicio, int fim, int[] array, AtomicInteger maior) {
        this.inicio = inicio;
        this.fim = fim;
        this.array = array;
        this.maior = maior;
    }

    @Override
    public Void call() {
        for (int i = inicio; i <= fim; i++) {
            lock.lock();
            if (array[i] > maior.get()) {
                maior.set(array[i]);
            }
            lock.unlock();
        }
        return null;
    }

    public static void main(String[] args) {
        int n_tarefas = 3;
        ExecutorService executor = Executors.newFixedThreadPool(3);
        AtomicInteger maior = new AtomicInteger(-9999);
        ArrayList<Callable<Void>> array_c = new ArrayList<Callable<Void>>();
        int[] array = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int n_por_tarefas = array.length / n_tarefas;
        int inicio = 0;

        for (int i = 0; i < n_por_tarefas - 1; i++) {
            array_c.add(new MaiorNumeroArray(inicio, inicio+n_por_tarefas-1, array, maior));
            inicio = inicio + n_por_tarefas;
        }
        array_c.add(new MaiorNumeroArray(inicio, array.length-1, array, maior));

        try {
            List<Future<Void>> futuros = executor.invokeAll(array_c);
            for(Future<Void> future: futuros){
                future.get();
            }
            System.out.println(maior.get());
            executor.shutdown();
        } catch(Exception e) {

        }


    }
}