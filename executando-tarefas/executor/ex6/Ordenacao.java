/*
Descrição:Fa ̧ca um programa que execute trˆes algoritmos de ordena ̧c ̃ao para umconjunto de valores e exiba o resultado apenas do algoritmo que finalizarprimeiro (useinvokeAny).
Aluna: Cláudia Sampedro
Data: 15/07/19
*/

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ordenacao {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        ArrayList<Callable<ArrayList>> array_c = new ArrayList<Callable<ArrayList>>();
        ArrayList<Integer> array = new ArrayList<Integer>();
        for (int i = 9; i>=0; i--){
            array.add(i);
        }
        System.out.println(array.toString());
        array_c.add(new BubbleSort(array));
        array_c.add(new Selection(array));
        array_c.add(new Insertion(array));
        
        try {
            ArrayList retorno = executor.invokeAny(array_c);
            System.out.println(retorno.toString());
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}

class BubbleSort implements Callable<ArrayList> {

    ArrayList<Integer> array;

    BubbleSort(ArrayList<Integer> array) {
        this.array = (ArrayList<Integer>)array.clone();
    }

    @Override
    public ArrayList call() throws Exception {
        int aux = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (array.get(j) > array.get(j + 1)) {
                    aux = array.get(j);
                    array.add(j, array.get(j + 1));
                    array.add(j + 1, aux);
                }
            }
        }
        return array;
    }

}

class Insertion implements Callable<ArrayList> {

    ArrayList<Integer> array;

    Insertion(ArrayList<Integer> array) {
        this.array = (ArrayList<Integer>)array.clone();
    }

    @Override
    public ArrayList call() throws Exception {
        for (int i = 1; i < array.size(); i++) {
            int aux = array.get(i);
            int j = i;

            while ((j > 0) && (array.get(j - 1) > aux)) {
                array.add(j, array.get(j - 1));
                j -= 1;
            }
            array.add(j, aux);
        }
        return array;
    }

}

class Selection implements Callable<ArrayList> {

    ArrayList<Integer> array;

    Selection(ArrayList<Integer> array) {
        this.array = (ArrayList<Integer>)array.clone();
    }

    @Override
    public ArrayList call() throws Exception {
        for (int fixo = 0; fixo < array.size() - 1; fixo++) {
            int menor = fixo;

            for (int i = menor + 1; i < array.size(); i++) {
                if (array.get(i) < array.get(menor)) {
                    menor = i;
                }
            }
            if (menor != fixo) {
                int t = array.get(fixo);
                array.add(fixo, array.get(menor));
                array.add(menor, t);
            }
        }
        return array;
    }

}