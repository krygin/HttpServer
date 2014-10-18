package http.message;

/**
 * Created by Ivan on 25.09.2014 in 18:16.
 */
public enum ContentType {
    HTML("text/html"),
    CSS("text/css"),
    JS("application/javascript"),
    JPG("image/jpeg"),
    JPEG("image/jpeg"),
    PNG("image/png"),
    GIF("image/gif"),
    SWF("application/x-shockwave-flash"),
    TXT("text/plain"),
    DEFAULT("application/octet-stream");

    private String mime;
    ContentType(String mime) {
        this.mime = mime;
    }

    @Override
    public String toString() {
        return mime;
    }
}