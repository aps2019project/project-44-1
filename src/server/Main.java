package server;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Main {
    private static ArrayList<Socket> sockets = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8000);
        Socket socket;
        System.out.println("server is running...");
        while (true) {
            socket = server.accept();
            sockets.add(socket);
            System.out.println("new client connected\nonline clients: " + sockets.size());
            new ClientHandler(socket);
        }
    }

    public static ArrayList<Socket> getSockets() {
        return sockets;
    }

}
