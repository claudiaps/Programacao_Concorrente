/*
Descrição: Fa ̧ca um programa usandoThreadseConcurrentMapparacalcular a frequˆencia de cada letra em um texto.
Aluna: Cláudia Sampedro
Data: 25/06/19
*/

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ThreadConcurrentMap {

    public static void main(String[] args) {
        ConcurrentHashMap<Character, Integer> frequencia = new ConcurrentHashMap<Character, Integer>();

        char[] text = "aabbccddee".toCharArray();
        Thread t1 = new ThreadCount(text, 0, text.length/2, frequencia);
        Thread t2 = new ThreadCount(text, text.length/2, text.length, frequencia);

        t1.start();
        t2.start();

        frequencia.forEach( (k, v) -> System.out.printf("    Letra: %s, Frequência: %s%n", k, v));
    }

}

class ThreadCount extends Thread {

    ConcurrentHashMap<Character, Integer> frequencia;
    char[] text;
    int inicio;
    int fim;

    ThreadCount(char[] text, int inicio, int fim, ConcurrentHashMap<Character, Integer> frequencia){
        this.text = text;
        this.inicio = inicio;
        this.fim = fim;
        this.frequencia = frequencia;
    }

    @Override
    public void run() {
        for(int i =inicio; i<fim; i++){
            int value = frequencia.getOrDefault(text[i], 0);
            value += 1;
            frequencia.put(text[i], value);
        }
    }

}