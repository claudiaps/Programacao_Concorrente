/*
Descrição: Fa ̧ca uma classe ArrayListThreadSafe usando ReadWriteLock.Teste usando threads que realizam leitura e escrita para essaestrutura
Aluna: Cláudia L P Sampedro
Data: 18/06
*/

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ArrayListThreadSafe {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock write = lock.writeLock();
    private final Lock read = lock.readLock();
    final ArrayList<Integer> array = new ArrayList<Integer>();

    int readThread(int index) {
        if (array.size() > 0) {
            try {
                read.lock();
                return array.get(index);
            } finally {
                read.unlock();
            }
        } 
        return -1;
    }

    void whiteTread(int value) {
        write.lock();
        array.add(value);
        write.unlock();
    }

    public static void main(String[] args) {
        ArrayListThreadSafe array = new ArrayListThreadSafe();
        Thread reader = new Reader(0, array);
        Thread reader1 = new Reader(1, array);
        Thread reader2 = new Reader(1, array);
        Thread writer = new Writer(10, array);
        Thread writer1 = new Writer(11, array);

        writer.start();
        reader.start();
        reader1.start();
        writer1.start();
        reader2.start();
    }
}

class Reader extends Thread {
    ArrayListThreadSafe array;
    int index;

    Reader(int index, ArrayListThreadSafe array) {
        this.index = index;
        this.array = array;
    }

    @Override
    public void run() {
        try {
            System.out.println(array.readThread(index));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class Writer extends Thread {
    ArrayListThreadSafe array;
    int value;

    Writer(int value, ArrayListThreadSafe array) {
        this.value = value;
        this.array = array;
    }

    @Override
    public void run() {
        try {
            System.out.println("Inserindo valor: " + value);
            array.whiteTread(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
