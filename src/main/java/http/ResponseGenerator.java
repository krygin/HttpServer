package http;

import http.message.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by Ivan on 23.09.2014 in 16:51.
 */
public class ResponseGenerator {
    private static Logger log = Logger.getLogger(ResponseGenerator.class.getName());
    private HttpRequest request;

    public ResponseGenerator(HttpRequest request) {
        this.request = request;
    }

    public HttpResponse getResponse(final String DOCUMENT_ROOT) throws IOException {

        HttpResponse response;
        if (request instanceof BadHttpRequest) {
            response = new HttpResponse(new ProtocolVersion("HTTP", 1, 1), new State(400, "Bad Request"));
            response.addHeader("Date", new Date().toString());
            response.addHeader("Server", "Krygin HTTP server");
            response.addHeader("Connection", "close");
            response.setMessageBody(new MessageBody("<html><head></head><body>400 Bad request</body></html>".getBytes()));
            return response;
        }

        if (request.getMethod() == Method.NOT_ALLOWED) {
            response = new HttpResponse(new ProtocolVersion("HTTP", 1, 1), new State(405, "Not allowed"));
            response.addHeader("Date", new Date().toString());
            response.addHeader("Server", "Krygin HTTP server");
            response.addHeader("Connection", "close");
            response.setMessageBody(new MessageBody("<html><head></head><body>405 Not allowed</body>e</html>".getBytes()));
            return response;
        }

        Path path;
        if (new File(DOCUMENT_ROOT + request.getPath()).isDirectory()) {
            path = Paths.get(DOCUMENT_ROOT + request.getPath() + "index.html");
        }
        else {
            path = Paths.get(DOCUMENT_ROOT + request.getPath());
        }

        byte[] bytes;
        if (path.toFile().exists()) {
            bytes = Files.readAllBytes(path);
        }
        else {
            response = new HttpResponse(new ProtocolVersion("HTTP", 1, 1), new State(404, "Not found"));
            response.addHeader("Date", new Date().toString());
            response.addHeader("Server", "Krygin HTTP server");
            response.addHeader("Connection", "close");
            if (request.getMethod() == Method.GET)
                response.setMessageBody(new MessageBody("<html><head></head><body>404 Not Found</body></html>".getBytes()));
            return response;
        }
        response = new HttpResponse(new ProtocolVersion("HTTP", 1, 1), new State(200, "Access"));
        response.addHeader("Date", new Date().toString());
        response.addHeader("Server", "Krygin HTTP server");
        response.addHeader("Connection", "close");
        response.addHeader("Content-Length", String.valueOf(bytes.length));
        response.addHeader("Content-Type", ContentTypeFactory.getContentType(path.getFileName().toString()).toString());
        if (request.getMethod() == Method.GET)
            response.setMessageBody(new MessageBody(bytes));
        return response;
    }
}