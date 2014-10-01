package http;

import http.message.MessageBody;
import http.message.ProtocolVersion;

/**
 * Created by Ivan on 25.09.2014 in 17:01.
 */
public interface HttpMessage {
    void addHeader(String name, String value);

    String getHeader(String name);

    ProtocolVersion getProtocolVersion();

    void setMessageBody(MessageBody messageBody);
}
