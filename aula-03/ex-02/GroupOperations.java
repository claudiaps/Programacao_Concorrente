/*
Atividades da aula de 01/04
Nome: Cláudia L P Sampedro
Descrição:  Fa ̧ca um programa em Java para testar um conjunto deopera ̧c ̃oes sobre um grupo de threads.  Use o ThreadGroup.
*/

public class GroupOperations implements Runnable{
    @Override
    public void run() {
        System.out.println("Starting a new Thread");
    }
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("First Thread Group");
        Thread t1 = new Thread(group, new GroupOperations());
        Thread t2 = new Thread(group, new GroupOperations());
        Thread t3 = new Thread(group, new GroupOperations());

        t1.start();
        t2.start();
        t3.start();

        System.out.println(group.activeCount());
        System.out.println(group.activeGroupCount());
        group.interrupt();

    }
}