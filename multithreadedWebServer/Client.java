package multithreadedWebServer;
import java.net.*;
import java.io.*;

public class Client {
    public Runnable getRunnable() {
        return () -> {
            int port = 8014;
            try {
                System.out.println("Client started... Connecting....");
                Socket socket = new Socket("localhost", port);
                System.out.println("Connected to: " + socket.getRemoteSocketAddress());
                socket.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
    }

    public static void main(String[] args) {
        Client client = new Client();
        for (int i = 0; i < 100; i++) {
            new Thread(client.getRunnable()).start();
        }
    }
}
