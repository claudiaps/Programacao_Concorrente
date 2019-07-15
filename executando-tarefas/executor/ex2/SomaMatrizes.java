/*
Descrição: Fa ̧ca um programa que calcule a soma dos elementos de uma matrizMxN. Divida o programa em tarefas que somam as linhas.  O programadeve possibilitar especificar o n ́umero de threads para resolver o problema
Aluna: Cláudia Sampedro
Data: 15/07/19
*/

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
import java.util.concurrent.Future;

public class SomaMatrizes implements Callable<Integer> {

    int[] linha;

    SomaMatrizes(int[] linha) {
        this.linha = linha;
    }

    @Override
    public Integer call() {
        int soma = 0;
        for (int i : linha) {
            soma += i;
        }
        return soma;
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        ArrayList<Callable<Integer>> array_c = new ArrayList<Callable<Integer>>();
        int[][] matriz = new int[][] { { 0, 1, 2, 0 }, { 3, 4, 5, 7 }, { 6, 7, 8, 9 } };

        for (int[] linha : matriz) {
            array_c.add(new SomaMatrizes(linha));

        }

        try {
            List<Future<Integer>> futuros = executor.invokeAll(array_c);
            int soma = 0;
            for (Future<Integer> future : futuros) {
                soma += future.get();
            }
            System.out.println(soma);
            executor.shutdown();
        } catch (Exception e) {

        }

    }
}