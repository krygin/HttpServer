/**
 * Created by Ivan on 27.09.2014 in 14:22.
 */
public class CommandLineParser {
    private String DOCUMENT_ROOT;
    private int THREAD_POOL_SIZE;
    private int PORT;

    public CommandLineParser(String[] args) {
        try {
            DOCUMENT_ROOT = args[0];
            PORT = Integer.parseInt(args[1]);
            THREAD_POOL_SIZE = Integer.parseInt(args[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CommandLineParser() {
        DOCUMENT_ROOT = "C:\\Users\\Ivan\\IdeaProjects\\HttpServer";
        PORT = 80;
        THREAD_POOL_SIZE = 10;
    }

    public String getDocumentRoot() {
        return DOCUMENT_ROOT;
    }

    public int getPort() {
        return PORT;
    }

    public int getThreadPoolSize() {
        return THREAD_POOL_SIZE;
    }
}
