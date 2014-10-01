package http;

import http.message.Method;

/**
 * Created by Ivan on 24.09.2014 in 20:23.
 */
public class BadHttpRequest extends BasicHttpMessage implements HttpRequest {

    protected BadHttpRequest(String version) {
        super(version);
    }

    @Override
    public String getPath() {
        return null;
    }

    @Override
    public Method getMethod() {
        return null;
    }
}