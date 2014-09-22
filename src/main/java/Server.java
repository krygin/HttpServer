import java.io.IOException;
import java.net.Socket;

/**
 * Created by Ivan on 17.09.2014 in 15:39.
 */
public class Server extends Thread {
    private ThreadPool threadPool;
    private ConnectionProcessor connectionProcessor;

    public Server() throws IOException {
        threadPool = new ThreadPool(10);
        connectionProcessor = new ConnectionProcessor(8080);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = connectionProcessor.acceptConnection();
                System.out.println("connection accepted");
                threadPool.addSocket(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
