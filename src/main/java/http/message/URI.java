package http.message;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ivan on 24.09.2014 in 17:51.
 */
public class URI {
    private String path;
    private Map<String, String> parameters = new HashMap<String, String>();


    public URI(String uri) {
        try {
            String decodedUrl = URLDecoder.decode(uri, "UTF-8");
            String[] parts = decodedUrl.split("\\?", 2);
            path = parts[0];
            if (parts.length == 2) {
                parts = parts[1].split("&");
                for (String part: parts) {
                    String parameter[] = part.split("=");
                    parameters.put(parameter[0], parameter[1]);
                }
            }
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public String getPath() {
        return path;
    }

    public String getParam(String name) {
        return parameters.get(name);
    }

    @Override
    public String toString() {
        StringBuilder paramsString = new StringBuilder("?");
        String param;
        for (String name: parameters.keySet()) {
            param = name+"="+parameters.get(name);
            paramsString.append(param + "&");
        }
        if (paramsString.length() > 0)
            paramsString.deleteCharAt(paramsString.length()-1);
        return path + paramsString.toString();
    }
}