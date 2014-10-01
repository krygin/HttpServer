package http.message;

/**
 * Created by Ivan on 24.09.2014 in 12:20.
 */
public class ProtocolVersion {
    String protocol;
    int major;
    int minor;

    public ProtocolVersion(String protocol, int major, int minor) {
        this.protocol = protocol;
        this.major = major;
        this.minor = minor;
    }

    public ProtocolVersion(String protocol) {
        try {
            String parts[] = protocol.split("/");
            this.protocol = parts[0];
            String[] version = parts[1].split("\\.");
            this.major = Integer.parseInt(version[0]);
            this.minor = Integer.parseInt(version[1]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return protocol + "/" + major + "." + minor;
    }


    public byte[] getBytes() {
        return (protocol + "/" + major + "." + minor).getBytes();
    }
}