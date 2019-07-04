package server;

import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private RequestHandler receiver;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.receiver = new RequestHandler(socket);
        receiver.start();
    }

}
