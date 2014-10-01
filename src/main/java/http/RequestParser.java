package http;

import http.message.MessageBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Ivan on 22.09.2014 in 19:47.
 */
public class RequestParser {
    private final InputStream inputStream;
    public RequestParser(InputStream inputStream) throws IOException {
        this.inputStream = inputStream;
    }

    public HttpRequest parse() throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String requestLine = bufferedReader.readLine();
            String[] requestLineFields = requestLine.split(" ");
            String header;
            BasicHttpRequest request = new BasicHttpRequest(requestLineFields[0], requestLineFields[1], requestLineFields[2]);

            while (((header = bufferedReader.readLine()) != null) && header.trim().length() != 0) {
                String[] headerFields = header.split(": ");
                request.addHeader(headerFields[0], headerFields[1]);
            }
            request.setMessageBody(new MessageBody());
            return request;
        }
        catch (Exception e) {
            return new BadHttpRequest("HTTP/1.1");
        }
    }
}