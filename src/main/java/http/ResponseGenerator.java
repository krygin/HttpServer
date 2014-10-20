package http;

import http.message.*;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.EnumSet;
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
            return response;
        }

        if (request.getMethod() == Method.NOT_ALLOWED) {
            response = new HttpResponse(new ProtocolVersion("HTTP", 1, 1), new State(405, "Not allowed"));
            response.addHeader("Date", new Date().toString());
            response.addHeader("Server", "Krygin HTTP server");
            response.addHeader("Connection", "close");
            return response;
        }
        Path requestPath = request.getPath();
        Path path = DOCUMENT_ROOT.resolve(requestPath);
        if (path.toFile().isDirectory()) {
            path = path.resolve("index.html");
            if (!Files.exists(path)) {
                response = new HttpResponse(new ProtocolVersion("HTTP", 1, 1), new State(403, "Forbidden"));
                response.addHeader("Date", new Date().toString());
                response.addHeader("Server", "Krygin HTTP server");
                response.addHeader("Connection", "close");
                return response;
            }
        }
        Path root1 = path.toRealPath();
        Path root2 = DOCUMENT_ROOT.toRealPath();
        System.out.println(root1);
        System.out.println(root2);
        if (!root1.startsWith(root2)) {
            response = new HttpResponse(new ProtocolVersion("HTTP", 1, 1), new State(403, "Forbidden"));
            response.addHeader("Date", new Date().toString());
            response.addHeader("Server", "Krygin HTTP server");
            response.addHeader("Connection", "close");
            return response;
        }
        FileChannel fileChannel;
        if (path.toFile().exists()) {
            fileChannel = FileChannel.open(path, EnumSet.of(StandardOpenOption.READ));
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
        response.addHeader("Content-Length", String.valueOf(fileChannel.size()));
        response.addHeader("Content-Type", ContentTypeFactory.getContentType(path.getFileName().toString()).toString());
        if (request.getMethod() == Method.GET)
            response.setMessageBody(new MessageBody(fileChannel));
        return response;
    }
}