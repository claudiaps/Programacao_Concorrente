/*
Atividades da aula de 26/03
Nome: Cláudia L P Sampedro
Descrição: Faca uma thread Java que fica aguardando uma sequˆencianum ́erica de tamanho arbitr ́ario digitado por usu ́ario pararealizar uma soma.  Use o join().
*/


import java.util.*;

public class JoinThreadSum extends Thread {

    int qtd;
    int[] number;

    JoinThreadSum(int qtd, int[] number) {
        this.number = number;
        this.qtd = qtd;
    }

    @Override
    public void run() {
        // number = new int[qtd];
        int soma = 0;
        for (int i = 0; i < qtd; i++) {
            soma = soma + number[i];
        }
        System.out.println("Sum: " + soma);
    }

    public static void main(String[] args) {
        int qtdNumbers = Integer.parseInt(args[0]);
        int[] numbers = new int[qtdNumbers];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < qtdNumbers; i++) {
            System.out.println("Number: ");
            int value = scanner.nextInt();
            numbers[i] = value;
            
        }
        Thread sum = new JoinThreadSum(qtdNumbers, numbers);
        sum.start();
        try {
            sum.join();
        } catch (Exception e) {
            //TODO: handle exception
        }
        System.out.println("Finished");


    }

}