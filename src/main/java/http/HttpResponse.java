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
        byte[] body = this.body.getBytes();
        byte[] result = new byte[headers.length + body.length];
        int i;
        for (i = 0; i < headers.length; i++) {
            result[i] = headers[i];
        }
        int a = 2 + 3;
        for (int j = 0; j < body.length; j++, i++) {
            result[i] = body[j];
        }
        return result;
    }
}