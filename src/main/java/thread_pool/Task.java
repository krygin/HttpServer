package thread_pool;

import http.HttpRequest;
import http.HttpResponse;
import http.RequestParser;
import http.ResponseGenerator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Created by Ivan on 22.09.2014 in 15:46.
 */
public class Task {
    private Socket socket;
    public Task(Socket socket) {
        this.socket = socket;
    }

    private static Logger log = Logger.getLogger(Task.class.getName());

    public void process(final String DOCUMENT_ROOT) throws IOException {
        HttpRequest request;
        HttpResponse response;
        InputStream inputStream = socket.getInputStream();

        RequestParser requestParser = new RequestParser(inputStream);
        request = requestParser.parse();
        ResponseGenerator responseGenerator = new ResponseGenerator(request);
        response = responseGenerator.getResponse(DOCUMENT_ROOT);

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(response.getBytes());

        socket.close();
    }

    public Socket getSocket() {
        return socket;
    }
}