package thread_pool;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Ivan on 21.09.2014 in 17:39.
 */
public class TaskQueue {
    private final Queue<Task> tasks = new ConcurrentLinkedQueue<Task>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask() {
        return tasks.poll();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}