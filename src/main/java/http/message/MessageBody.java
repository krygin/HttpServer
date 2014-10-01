package http.message;

/**
 * Created by Ivan on 22.09.2014 in 19:32.
 */
public class MessageBody {
    private final byte[] body;

    public MessageBody(byte[] body) {
        this.body = body;
    }

    public MessageBody() {
        this.body = "".getBytes();
    }

    @Override
    public String toString() {
        return new String(body);
    }

    public byte[] getBytes() {
        return body;
    }
}