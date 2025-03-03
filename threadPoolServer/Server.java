package threadPoolServer;

import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final ExecutorService threadPool;

    public Server(int poolSize) {
        this.threadPool = Executors.newFixedThreadPool(poolSize);
    }

    public void handleClient(Socket clientSocket) {
        try (PrintWriter toSocket = new PrintWriter(clientSocket.getOutputStream(), true)) {
            toSocket.println("Hello from server " + clientSocket.getInetAddress());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 8016;
        int poolSize = 10;
        Server server = new Server(poolSize);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port: " + port);
            while (true) {
                Socket acceptedSocket = serverSocket.accept();
                server.threadPool.execute(() -> server.handleClient(acceptedSocket));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            server.threadPool.shutdown();
        }
    }
}
