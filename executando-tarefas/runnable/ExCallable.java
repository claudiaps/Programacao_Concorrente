/* 
Decrição:  Implemente um programa que calcule o fatorial de um n ́umeroem uma thread usando o Callable
Aluna: Cláudia Sampedro
Data: 01/07/2019
*/

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExCallable {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> result2 = executor.submit(new UsingCallable(5));

        try{
            System.out.println(result2.get());
        } catch(Exception e){
        } finally{
            executor.shutdown();
        }
    }

}

class UsingCallable implements Callable<Integer> {

    int value;
    int result = 1;

    UsingCallable(int value) {
        this.value = value;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Executing Callable...");

        for (int i = 1; i <= value; i++) {
            result = result * i;
        }
        return result;

    }
}
