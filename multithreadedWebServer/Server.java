package multithreadedWebServer;
import java.net.*;
import java.io.*;
import java.util.function.Consumer;

public class Server {
    public Consumer<Socket> getConsumer() {
        return clientSocket -> {
            try {
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
                toClient.println("Hello from server");
                clientSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        };
    }

    public static void main(String[] args) {
        Server server = new Server();
        try (ServerSocket serverSocket = new ServerSocket(8014)) {
            System.out.println("Server is running on port: 8014");
            while (true) {
                Socket acceptedSocket = serverSocket.accept();
                new Thread(() -> server.getConsumer().accept(acceptedSocket)).start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
