import java.io.IOException;

/**
 * Created by Ivan on 12.09.2014 in 15:03.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = new Server();
        server.start();
        server.join();
    }
}