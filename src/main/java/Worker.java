import java.io.IOException;
import java.net.Socket;

/**
 * Created by Ivan on 17.09.2014 in 16:33.
 */
public class Worker extends Thread {
    private SocketQueue sockets;

    public Worker(SocketQueue sockets) {
        this.sockets = sockets;
    }

    @Override
    public void run() {
        Socket socket;
        while (true) {
            synchronized (sockets) {
                while (sockets.isEmpty()) {
                    try {
                        sockets.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                socket = sockets.getSocket();
            }
            try {
                System.out.println("Sockets processed: " + socket.toString());
                System.out.println(Thread.currentThread().toString());
                socket.close();
            }
            catch (RuntimeException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}