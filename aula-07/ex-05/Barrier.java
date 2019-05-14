import java.util.concurrent.Semaphore;

public class Barrier extends Thread{

    Semaphore mutex;
    Semaphore barrier;
    Value valor;
    int value_barrier = 0;

    Barrier(Semaphore mutex, Value valor, Semaphore barrier){
        this.mutex = mutex;
        this.valor = valor;
        this.barrier = barrier;
    }

    @Override
    public void run(){
        try {
            mutex.acquire();
        } catch (Exception e) {
            //TODO: handle exception
        }
        valor.incrementValue();
        System.out.println(valor);
        mutex.release();
        value_barrier++;
        

    }

    public static void main(String[] args) {
        
    }

}

class Value{
    int value = 0;

    int incrementValue(){
        value++;
        return value;
    }

    int getValue(){
        return value;
    }

}