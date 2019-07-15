
/*
Descrição:Fa ̧ca um programa que execute trˆes algoritmos de ordena ̧c ̃ao para umconjunto de valores e exiba o resultado apenas do algoritmo que finalizarprimeiro (useinvokeAny).
Aluna: Cláudia Sampedro
Data: 15/07/19
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ordenacao {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        ArrayList<Callable<Integer[]>> array_c = new ArrayList<Callable<Integer[]>>();
        Integer[] array = new Integer[]{9,8,7,6,5,4,3,2,1};
        System.out.println(Arrays.toString(array));
        array_c.add(new BubbleSort(array));
        array_c.add(new Selection(array));
        array_c.add(new Insertion(array));
        
        try {
            Integer[] retorno = executor.invokeAny(array_c);
            System.out.println(Arrays.toString(retorno));
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}

class BubbleSort implements Callable<Integer[]> {

    Integer[] array;

    BubbleSort(Integer[] array) {
        this.array = array.clone();
    }

    @Override
    public Integer[] call() throws Exception {
        int aux = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (array[j] > array[j+1]) {
                    aux = array[j];
                    array[j] = array[j+1];
                    array[j+1] = aux;
                }
            }
        }
        return array;
    }

}

class Insertion implements Callable<Integer[]>{

    Integer[] array;

    Insertion(Integer[] array) {
        this.array = array.clone();
    }

    @Override
    public Integer[] call() throws Exception {
        for (int i = 1; i < array.length; i++) {
            int aux = array[i];
            int j = i;

            while ((j > 0) && (array[j-1] > aux)) {
                array[j] = array[j-1];
                j -= 1;
            }
            array[j] = aux;
        }
        return array;
    }

}

class Selection implements Callable<Integer[]> {

    Integer[] array;

    Selection(Integer[] array) {
        this.array = array.clone();
    }

    @Override
    public Integer[] call() throws Exception {
        for (int fixo = 0; fixo < array.length - 1; fixo++) {
            int menor = fixo;

            for (int i = menor + 1; i < array.length; i++) {
                if (array[i] < array[menor]) {
                    menor = i;
                }
            }
            if (menor != fixo) {
                int t = array[fixo];
                array[fixo] = array[menor];
                array[menor] = t;
            }
        }
        return array;
    }

}