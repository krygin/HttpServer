package thread_pool;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 17.09.2014 in 16:32.
 */
public class Workers {
    private final List<Worker> workers = new ArrayList<Worker>();

    public Workers(TaskQueue sockets, final int THREAD_POOL_SIZE, Path DOCUMENT_ROOT) {
        for (int i = 0; i < THREAD_POOL_SIZE; i++) {
            Worker worker = new Worker(sockets, DOCUMENT_ROOT);
            workers.add(worker);
            worker.start();
        }
        System.out.println(workers.size());
    }
}
