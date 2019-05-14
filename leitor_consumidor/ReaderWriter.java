import java.util.concurrent.Semaphore;

public class ReaderWriter {

    int numReaders = 0;
    Semaphore mutex = new Semaphore(1);
    Semaphore wlock = new Semaphore(1);

    public void startRead() throws InterruptedException {
        mutex.acquire();
        if (numReaders == 0) {
            wlock.acquire();
            numReaders++;
        }
        mutex.release();
        //realiza a leitura
    }

    public void endRead() throws InterruptedException {
        if (numReaders == 0) {
            wlock.release();
        } else {
            numReaders--;
        }
        //checa se não tem ninguem mais lendo pra liberar o wlock
    }

    public void startWrite() throws InterruptedException {
        wlock.acquire();
    }

    public void endWrite() {
        wlock.release();
    }

    public static void main(String[] args) {
        
        
    }

}

class Writer extends Thread{

    Value value;
    ReaderWriter controller;

    Writer(Value value, ReaderWriter controller){
        this.value = value;
        this.controller = controller;
    }

    @Override
    public void run() {
        controller.startWrite();
        value.incrementValue();
        controller.endWrite();
    }
}

class Reader extends Thread{

    Value value;
    ReaderWriter controller;

    Reader(Value value, ReaderWriter controller){
        this.value = value;
        this.controller = controller;
    }

    @Override
    public void run() {
        controller.startRead();
        System.out.println("Value: " + value);
        controller.endRead();
    }
}

class Value{
    int value = 0;

    int incrementValue(){
        value++;
        return value;
    }
}