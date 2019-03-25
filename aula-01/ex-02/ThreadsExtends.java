public class ThreadsExtends extends Thread{

    @Override
    public void run (){
        System.out.println("Hello from a new thread with extends Thread");
    }

    public static void main(String[] args) {
        int nThreads  = Integer.parseInt(args[0]);

        for (int i=0; i<nThreads; i++){
            new ThreadsExtends().start();
        }
    }

}