package http.message;

/**
 * Created by Ivan on 22.09.2014 in 19:22.
 */
public class State {
    private int code;
    private String description;
    public State(int code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String toString() {
        return code + " " + description;
    }

    public byte[] getBytes(){
        return (code + " " + description).getBytes();
    }
}