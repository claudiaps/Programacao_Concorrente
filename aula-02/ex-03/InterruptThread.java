import java.io.*;
import java.util.Random;



class PrintFile extends Thread {

    String file_path;

    PrintFile(String file_path) {
        this.file_path = file_path;
    }

    @Override
    public void run() {
        System.out.println("Read a file and printing a line at 10s");
        try {
            File file = new File(file_path);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null){
                System.out.println(st);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // public static void main(String[] args) {
    //     String path = "/home/claudiasampedro/Documentos/7_periodo/Prog_Concorrente/Programacao_Concorrente/aula-02/ex-02/texto.txt";
    //     new PrintFile(path).start();
    // }

}

class RandomTimeFinish extends Thread {

    @Override
    public void run() {
        System.out.println("Hello from a new thread");
        try {
            Random rand = new Random();
            int value = rand.nextInt(100) + 1;
            Thread.sleep(value);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // public static void main(String[] args) {
    //     for (int i = 0; i < 3; i++) {
    //         new RandomTimeFinish().start();
    //     }
    // }

}

public class InterruptThread extends Thread{

    public static void main(String[] args) {
        String path = "/home/claudiasampedro/Documentos/7_periodo/Prog_Concorrente/Programacao_Concorrente/aula-02/ex-02/texto.txt";
        Thread ex1 = new RandomTimeFinish();
        Thread ex2 = new PrintFile(path);
        ex1.start();
        ex2.start();
        
        ex1.interrupt();
        ex2.interrupt();
    }
    
}