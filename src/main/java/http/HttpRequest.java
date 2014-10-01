package http;

import http.message.Method;

/**
 * Created by Ivan on 24.09.2014 in 20:33.
 */
public interface HttpRequest extends HttpMessage {
    public String getPath();
    public Method getMethod();
}