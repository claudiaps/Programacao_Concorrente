
/*
Descrição: Fa ̧ca um programa que periodicamente monitore mudan ̧cas em umconjunto de arquivos especificados.  Se ocorreram mudan ̧cas, o programadeve registr ́a-las em um arquivo de log
Aluna: Cláudia Sampedro
Data: 15/07/19
*/

import java.nio.file.WatchService;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.util.concurrent.Executors;
import java.nio.file.Path;
import java.io.BufferedWriter;
import java.util.concurrent.ExecutorService;
import java.io.File;
import java.nio.file.WatchKey;
import java.nio.file.WatchEvent;
import java.util.List;

public class WatchFiles implements Runnable {

    Path diretorio;
    WatchService monitor;
    PrintWriter printWriter;

    WatchFiles(String p) {
        try {
            diretorio = Paths.get(p);
            monitor = diretorio.getFileSystem().newWatchService();
            diretorio.register(monitor, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_MODIFY);
            FileWriter writer = new FileWriter(new File("/home/claudiasampedro/Documentos/7_periodo/Prog_Concorrente/Programacao_Concorrente/executando-tarefas/executor/ex4/log.txt"), true);
            BufferedWriter buffer = new BufferedWriter(writer);
            printWriter = new PrintWriter(buffer);
            printWriter.println("Log");
            printWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
		while (true) {
			try {
				WatchKey monitorKey = monitor.take();
				List<WatchEvent<?>> events = monitorKey.pollEvents();
				for (WatchEvent<?> event : events) {
                    printWriter.println(event.context().toString());
				}
				printWriter.flush();
				monitorKey.reset();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new WatchFiles("/home/claudiasampedro/Documentos/7_periodo/Prog_Concorrente/Programacao_Concorrente/executando-tarefas/executor/ex4/files"));
    }
}