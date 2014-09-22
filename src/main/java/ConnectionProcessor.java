import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Ivan on 20.09.2014 in 23:01.
 */
public class ConnectionProcessor {
    private ServerSocket serverSocket;

    public ConnectionProcessor(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public Socket acceptConnection() throws IOException {
        return serverSocket.accept();
    }
}