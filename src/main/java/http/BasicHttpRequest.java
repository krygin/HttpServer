package http;

import http.message.*;

/**
 * Created by Ivan on 22.09.2014 in 16:04.
 */
public class BasicHttpRequest extends BasicHttpMessage implements HttpRequest {
    private Method method;
    private URI uri;
    public BasicHttpRequest(String method, String uri, String version) {
        super(version);
        try {
            this.method = Method.valueOf(method);
        }
        catch (IllegalArgumentException e) {
            this.method = Method.NOT_ALLOWED;
        }
        this.uri = new URI(uri);
    }

    public BasicHttpRequest(Method method, URI uri, ProtocolVersion protocolVersion) {
        super(protocolVersion);
        this.method = method;
        this.uri = uri;
    }

    public String getPath() {
        return uri.getPath();
    }

    public Method getMethod() {
        return method;
    }

    public void addHeader(String name, String value) {
        headers.addHeader(name, value);
    }

    public String getHeader(String name) {
        return headers.getHeader(name);
    }

    @Override
    public String toString() {
        return method + " " + uri + " " + protocolVersion + "\n" +
                headers + "\n" +
                body + "\n";
    }
}