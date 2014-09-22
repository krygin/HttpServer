import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Ivan on 21.09.2014 in 17:39.
 */
public class SocketQueue {
    private final Queue<Socket> sockets = new ConcurrentLinkedQueue<Socket>();

    public void addSocket(Socket socket) {
        sockets.add(socket);
    }

    public Socket getSocket() {
        return sockets.poll();
    }

    public boolean isEmpty() {
        return sockets.isEmpty();
    }
}