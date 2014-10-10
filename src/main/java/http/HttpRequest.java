package http;

import http.message.Method;

import java.nio.file.Path;

/**
 * Created by Ivan on 24.09.2014 in 20:33.
 */
public interface HttpRequest extends HttpMessage {
    public Path getPath();
    public Method getMethod();
}