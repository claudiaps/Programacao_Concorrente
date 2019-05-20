import java.util.concurrent.Semaphore;

public class ReaderWriter {

    int numReaders = 0;
    int numWriters = 0;
    Semaphore internal_wlock = new Semaphore(1);
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
        mutex.acquire();
        numReaders--;
        if (numReaders == 0) {
            wlock.release();
        }
        mutex.release();
        //checa se não tem ninguem mais lendo para liberar o wlock
    }

    public void startWrite() throws InterruptedException {
        mutex.acquire();
        if(numWriters == 0){
            internal_wlock.acquire();
            numWriters++;
        }
        wlock.acquire();
    }

    public void endWrite()throws InterruptedException  {
        mutex.acquire();
        numWriters--;
        if(numWriters == 0){
            internal_wlock.release();
        }
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
                value.incrementValue();
                controller.endWrite();
            } catch (Exception e) {
                //TODO: handle exception
            }
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