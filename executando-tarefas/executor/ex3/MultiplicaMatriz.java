
/*
Descrição: Fa ̧ca um programa concorrente para multiplicar duas matrizes.
Aluna: Cláudia Sampedro
Data: 15/07/19
*/

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;
import java.util.concurrent.Future;

public class MultiplicaMatriz implements Callable<Integer> {

    int[][] matriz1;
    int[][] matriz2;
    int i;
    int j;

    MultiplicaMatriz(int[][] matriz1, int[][] matriz2, int i, int j) {
        this.matriz1 = matriz1;
        this.matriz2 = matriz2;
        this.j = j;
        this.i = i;
    }

    @Override
    public Integer call() {
        int soma = 0;
        for (int x = 0; x < 3; x++) {
            soma += matriz1[i][x] * matriz2[x][j];
        }
        return soma;
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        ArrayList<Callable<Integer>> array_c = new ArrayList<Callable<Integer>>();
        int[][] matriz1 = new int[][] { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };
        int[][] matriz2 = new int[][] { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array_c.add(new MultiplicaMatriz(matriz1, matriz2, i, j));
            }
        }

        try {
            List<Future<Integer>> futuros = executor.invokeAll(array_c);
            int pos = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.println(futuros.get(pos).get());
                    pos += 1;
                }
            }
            executor.shutdown();
        } catch (Exception e) {

        }

    }
}