import java.util.concurrent.Semaphore;

/*
Nome: Cláudia L P Sampedro
Data: 30/04
Descrição:Garantir acesso exclusivo à seção crítica.Faça um código que possibilite que 2 ou mais threads realizem o incremento de um contador. Faça aexclusão mútua com semáforo.
*/

class Value{
    
    Semaphore semaforo = new Semaphore(1);    
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

public class Mutex extends Thread {

    Value value;

    Mutex(Value value) {
        this.value = value;
    }

    @Override
    public void run() {
        System.out.println("new value1: " + value.IncrementValue());
    }

    public static void main(String[] args) {
        Value value = new Value();
        Thread t1 = new Mutex(value);
        Thread t2 = new Mutex2(value);
        t1.start();
        t2.start();

    }

}

class Mutex2 extends Thread {

    Value value;

    Mutex2(Value value) {
        this.value = value;
    }

    @Override
    public void run() {
        System.out.println("new value2: " + value.IncrementValue());
    }


}