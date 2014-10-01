import java.io.IOException;

/**
 * Created by Ivan on 12.09.2014 in 15:03.
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        CommandLineParser parser = new CommandLineParser(args);
        Server server = new Server(parser.getPort(), parser.getThreadPoolSize(), parser.getDocumentRoot());
        server.start();
        server.join();
    }
}