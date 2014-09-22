import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 17.09.2014 in 16:32.
 */
public class Workers {
    private final List<Worker> workers = new ArrayList<Worker>();

    public Workers(SocketQueue sockets, int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker(sockets);
            workers.add(worker);
            worker.start();
        }

    }
}
