
/*
Descrição: Faca um programa que possibilite agendar uma tarefa para ser executadaem um hor ́ario espec ́ıfico.
Aluna: Cláudia Sampedro
Data: 15/07/19
*/

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TarefaAgendada implements Runnable {

    public void run() {
      System.out.println("Tarefa agendada sendo executada");
    }

    public static void main(String[] args) {
      ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.HOUR, 6);
      calendar.set(Calendar.MINUTE, 27);
  
      long segundos = (calendar.getTime().getTime() - new Date().getTime()) / 1000;
      ScheduledFuture future = executor.schedule(new TarefaAgendada(), segundos, TimeUnit.SECONDS);

      try {
        future.get();
      } catch (Exception e) {
        e.printStackTrace();
      }  

      executor.shutdown();
    }
  }