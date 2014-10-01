package http;

import http.message.Headers;
import http.message.MessageBody;
import http.message.ProtocolVersion;

/**
 * Created by Ivan on 22.09.2014 in 17:36.
 */
public abstract class BasicHttpMessage implements HttpMessage {
    protected Headers headers;
    protected ProtocolVersion protocolVersion;
    protected MessageBody body;

    protected BasicHttpMessage(ProtocolVersion protocolVersion) {
        this.protocolVersion = protocolVersion;
        this.headers = new Headers();
        this.body = new MessageBody("".getBytes());
    }

    protected BasicHttpMessage(String version) {
        this(new ProtocolVersion(version));
    }



    @Override
    public void addHeader(String name, String value) {
        headers.addHeader(name, value);
    }

    @Override
    public String getHeader(String name) {
        return headers.getHeader(name);
    }

    @Override
    public ProtocolVersion getProtocolVersion(){
        return protocolVersion;
    }

    @Override
    public void setMessageBody(MessageBody messageBody) {
        this.body = messageBody;
    }
}