public class ThreadsRunnable implements Runnable{

    @Override
    public void run(){
        System.out.println("Hello from a thread with runnable");
    }

    public static void main(String[] args) {
        int nThreads = Integer.parseInt(args[0]);

        for (int i =0; i< nThreads; i++){
            new Thread(new ThreadsRunnable()).start();
        }
    }

}