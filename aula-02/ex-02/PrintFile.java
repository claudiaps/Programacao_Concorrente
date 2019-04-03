import java.io.*;

public class PrintFile extends Thread {

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

    public static void main(String[] args) {
        String path = "/home/claudiasampedro/Documentos/7_periodo/Prog_Concorrente/Programacao_Concorrente/aula-02/ex-02/texto.txt";
        new PrintFile(path).start();
    }

}