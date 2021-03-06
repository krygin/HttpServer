package thread_pool;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by Ivan on 20.09.2014 in 14:08.
 */
public class ThreadPool {
    private final TaskQueue tasks = new TaskQueue();
    private final Workers workers;

    public ThreadPool(final int THREAD_POOL_SIZE, Path DOCUMENT_ROOT) throws IOException {
        if (THREAD_POOL_SIZE == 0) {
            throw new IllegalArgumentException("Pool size can't be zero.");
        }
        workers = new Workers(tasks, THREAD_POOL_SIZE, DOCUMENT_ROOT);
    }

    public void addTask(Task task) {
        synchronized (tasks) {
            tasks.addTask(task);
            tasks.notifyAll();
        }
    }
}