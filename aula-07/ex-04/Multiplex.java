import java.util.concurrent.Semaphore;

/*
Nome: Cláudia L P Sampedro
Data: 30/04
Descrição:Garantir acesso exclusivo à seção crítica.Faça um código que possibilite que 2 ou mais threads realizem o incremento de um contador. Faça aexclusão mútua com semáforo.
*/

class Value{
    
    Semaphore semaforo = new Semaphore(4);    
    int value = 0;

    int IncrementValue(){
        try {
            semaforo.acquire();
            value++;
            semaforo.release();
            return value;
        } catch (Exception e) {
            //TODO: handle exception
        }
        return -1;
    }

}

public class Multiplex extends Thread {

    Value value;

    Multiplex(Value value) {
        this.value = value;
    }

    @Override
    public void run() {
        System.out.println("new value: " + value.IncrementValue());
    }

    public static void main(String[] args) {
        Value value = new Value();
        Thread t1 = new Multiplex(value);
        Thread t2 = new Multiplex(value);
        Thread t3 = new Multiplex(value);
        Thread t4 = new Multiplex(value);
        Thread t5 = new Multiplex(value);
        Thread t6 = new Multiplex(value);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();


    }

}