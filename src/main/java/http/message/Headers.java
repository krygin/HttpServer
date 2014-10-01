package http.message;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ivan on 22.09.2014 in 17:40.
 */
public class Headers {
    private Map<String, String> headers = new HashMap<String, String>();

    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    public String getHeader(String name) {
        return headers.get(name);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String key: headers.keySet()) {
            stringBuilder.append(key + ": " + headers.get(key) + "\r\n");
        }
        return stringBuilder.toString();
    }

    public byte[] getBytes() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String key: headers.keySet()) {
            stringBuilder.append(key + ": " + headers.get(key) + "\r\n");
        }
        return stringBuilder.toString().getBytes();
    }
}