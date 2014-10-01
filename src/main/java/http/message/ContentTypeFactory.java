package http.message;

/**
 * Created by Ivan on 25.09.2014 in 18:28.
 */
public class ContentTypeFactory {
    public static ContentType getContentType(String filename) {
        String [] parts = filename.split("\\.");
        String extension = parts[parts.length - 1];
        return ContentType.valueOf(extension.toUpperCase());
    }
}
