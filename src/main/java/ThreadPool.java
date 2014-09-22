import java.io.IOException;
import java.net.Socket;

/**
 * Created by Ivan on 20.09.2014 in 14:08.
 */
public class ThreadPool {
    private final SocketQueue sockets = new SocketQueue();
    private final Workers workers;

    public ThreadPool(int poolSize) throws IOException {
        if (poolSize == 0) {
            throw new IllegalArgumentException("Pool size can't be zero.");
        }
        workers = new Workers(sockets, poolSize);
    }

    public void addSocket(Socket socket) {
        synchronized (sockets) {
            sockets.addSocket(socket);
            sockets.notify();
        }
    }
}