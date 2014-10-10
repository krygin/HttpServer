package http;

import http.message.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public HttpResponse getResponse(Path DOCUMENT_ROOT) throws IOException {

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
        Path requestPath = request.getPath();
        Path path = DOCUMENT_ROOT.resolve(requestPath);
        if (path.toFile().isDirectory())
            path = path.resolve("index.html");
        Path root1 = path.toAbsolutePath();
        Path root2 = DOCUMENT_ROOT.toAbsolutePath();

        if (!root1.toString().contains((root2.toString()))) {
            response = new HttpResponse(new ProtocolVersion("HTTP", 1, 1), new State(403, "Forbidden"));
            response.addHeader("Date", new Date().toString());
            response.addHeader("Server", "Krygin HTTP server");
            response.addHeader("Connection", "close");
            return response;
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