package thread_pool;

import java.io.IOException;

/**
 * Created by Ivan on 17.09.2014 in 16:33.
 */
public class Worker extends Thread {
    private final String DOCUMENT_ROOT;
    private final TaskQueue tasks;

    public Worker(TaskQueue tasks, final String DOCUMENT_ROOT) {
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
            catch (RuntimeException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}