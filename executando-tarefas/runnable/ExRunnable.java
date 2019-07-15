/* 
Decrição:  Implemente um programa que calcule o fatorial de um n ́umeroem uma thread usando o Runnable
Aluna: Cláudia Sampedro
Data: 01/07/2019
*/


import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExRunnable {

    public static void main(String[] args) {

        Result result = new Result();

        Thread runnable = new Thread(new UsingRunnable(5, result));
        runnable.start();
        try {
            runnable.join();
        } catch (Exception e) {
        }

        System.out.println(result.get());
    }

}

class UsingRunnable implements Runnable {

    int value;
    Result result;

    UsingRunnable(int value, Result result) {
        this.value = value;
        this.result = result;
    }

    @Override
    public void run() {
        System.out.println("Executing Runnable...");

        for (int i = 1; i <= value; i++) {
            result.set(result.get() * i);
        }

    }
}

class Result {
    int value = 1;

    int get() {
        return value;
    }

    void set(int new_value) {
        value = new_value;
    }

}