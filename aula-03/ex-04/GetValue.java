/*
Atividades da aula de 01/04
Nome: Cláudia L P Sampedro
Descrição:  Fa ̧ca um programa em Java que realize uma busca paralelaem um vetor de inteiros.  Informe para o m ́etodo:  valorprocurado, vetor de inteiros e o n ́umero de threads.
*/


import java.util.*;

public class GetValue extends Thread {

    ArrayList<Integer> values;
    int value;
    // int begin;
    // int end;

    GetValue(ArrayList<Integer> values, int value) {
        this.value = value;
        this.values = values;
        // this.begin = begin;
        // this.end = end;
    }

    @Override
    public void run() {
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i) == value) {
                System.out.println("Valor encontrado: " + value);
                return;
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> values = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            values.add(i);
        }

        int value = 19;
        int n_threads = Integer.parseInt(args[0]);
        int range = values.size() / n_threads;
        int begin = 0;
        int end = range;

        for (int j = 0; j <n_threads-1; j++) {
            ArrayList<Integer> sub_values = new ArrayList<>();
            for (int k = begin; k<end; k++){
                sub_values.add(values.get(k));
            }
            begin = end + 1;
            end = end + range;

            Thread t = new GetValue(sub_values, value);
            t.start();
        }

        ArrayList<Integer> sub_values = new ArrayList<>();
        for (int k = end; k<values.size(); k++){
            sub_values.add(values.get(k));
        }
        Thread t = new GetValue(sub_values, value);
        t.start();


    }

}