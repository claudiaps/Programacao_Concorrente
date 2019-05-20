import java.util.concurrent.Semaphore;

public class ReaderWriter {

    int numReaders = 0;
    Semaphore mutex = new Semaphore(1);
    Semaphore wlock = new Semaphore(1);
    Semaphore queue = new Semaphore(1, true);

    public void startRead() throws InterruptedException {
        queue.acquire();
        mutex.acquire();
        if (numReaders == 0) {
            wlock.acquire();
            numReaders++;
        }
        mutex.release();
        queue.release();
        //realiza a leitura
    }

    public void endRead() throws InterruptedException {
        mutex.acquire();
        numReaders--;
        if (numReaders == 0) {
            wlock.release();
        }
        mutex.release();
        //checa se não tem ninguem mais lendo para liberar o wlock
    }

    public void startWrite() throws InterruptedException {
        queue.acquire();
        wlock.acquire();
        queue.release();
    }

    public void endWrite() {
        wlock.release();
    }

    public static void main(String[] args) {
        
        ReaderWriter controller = new ReaderWriter();
        Value value = new Value();

        Thread writer1 = new Writer(value, controller);
        Thread writer2 = new Writer(value, controller);
        Thread reader1 = new Reader(value, controller);
        Thread reader2 = new Reader(value, controller);


        writer1.start();
        reader1.start();
        writer2.start();
        reader2.start();
    }

}

class Writer extends Thread {

    Value value;
    ReaderWriter controller;

    Writer(Value value, ReaderWriter controller){
        this.value = value;
        this.controller = controller;
    }

    @Override
    public void run() {
        while(true){

            try {
                controller.startWrite();
            } catch (Exception e) {
                //TODO: handle exception
            }
            value.incrementValue();
            controller.endWrite();
        }
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
        while (true){
            try {
                controller.startRead();
                System.out.println("Value: " + value.ValueIs());
                controller.endRead();
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
    }
}

class Value{
    int value = 0;

    int ValueIs(){
        return value;
    }

    int incrementValue(){
        value++;
        return value;
    }
}