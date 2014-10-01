import thread_pool.Task;
import thread_pool.ThreadPool;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Ivan on 17.09.2014 in 15:39.
 */
public class Server extends Thread {
    private ThreadPool threadPool;
    private ConnectionProcessor connectionProcessor;

    public Server(final int PORT, final int THREAD_POOL_SIZE, final String DOCUMENT_ROOT) throws IOException {
        threadPool = new ThreadPool(THREAD_POOL_SIZE, DOCUMENT_ROOT);
        connectionProcessor = new ConnectionProcessor(PORT);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = connectionProcessor.acceptConnection();
                threadPool.addTask(new Task(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
