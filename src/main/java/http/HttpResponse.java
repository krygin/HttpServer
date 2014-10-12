package http;

import http.message.ProtocolVersion;
import http.message.State;

/**
 * Created by Ivan on 23.09.2014 in 16:55.
 */
public class HttpResponse extends BasicHttpMessage {
    private State state;

    public HttpResponse(ProtocolVersion protocolVersion, State state) {
        super(protocolVersion);
        this.state = state;
    }

    @Override
    public String toString() {
        return protocolVersion + " " + state + "\r\n" + headers + "\r\n" + body + "\r\n";
    }

    public byte[] getBytes() {
        byte[] headers = (protocolVersion + " " + state + "\r\n" + this.headers + "\r\n").getBytes();

        return headers;
    }
}