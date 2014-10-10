package thread_pool;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by Ivan on 17.09.2014 in 16:33.
 */
public class Worker extends Thread {
    private final Path DOCUMENT_ROOT;
    private final TaskQueue tasks;

    public Worker(TaskQueue tasks, Path DOCUMENT_ROOT) {
        this.tasks = tasks;
        this.DOCUMENT_ROOT = DOCUMENT_ROOT;
    }

    @Override
    public void run() {
        Task task;
        while (true) {
            synchronized (tasks) {
                while (tasks.isEmpty()) {
                    try {
                        tasks.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                task = tasks.getTask();
            }
            try {
                task.process(DOCUMENT_ROOT);
            }
            catch (RuntimeException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}