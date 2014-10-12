import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by Ivan on 20.09.2014 in 23:01.
 */
public class ConnectionProcessor {
    private ServerSocketChannel serverSocketChannel;

    public ConnectionProcessor(int port) throws IOException {
        this.serverSocketChannel = ServerSocketChannel.open().bind(new InetSocketAddress(port));
        this.serverSocketChannel.configureBlocking(false);
    }

    public Socket acceptConnection() throws IOException {
        SocketChannel socketChannel = serverSocketChannel.accept();
        return socketChannel != null ? socketChannel.socket() : null;
    }
}