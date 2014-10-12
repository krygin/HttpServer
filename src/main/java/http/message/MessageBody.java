package http.message;

import java.nio.channels.FileChannel;

/**
 * Created by Ivan on 22.09.2014 in 19:32.
 */
public class MessageBody {
    private final FileChannel fileChannel;

    public MessageBody(FileChannel fileChannel) {
        this.fileChannel = fileChannel;
    }

    public MessageBody() {
        this.fileChannel = null;
    }

    public FileChannel getFileChannel() {
        return fileChannel;
    }
}