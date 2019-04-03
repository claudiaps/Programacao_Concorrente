/*
Atividades da aula de 25/03
Nome: Cláudia L P Sampedro
Descrição: Implemente o exemplo anterior usando Lambda Expression.
*/

public class LambdaThread{

    public static void main(String[] args) {
        new Thread( () -> {
            System.out.println("Hello from a Thread!");
        }).start();
    } 
}