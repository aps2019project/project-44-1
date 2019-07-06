package server;

import models.Account;
import models.Game;

import javax.management.ObjectName;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    private static ArrayList<Socket> sockets = new ArrayList<>();
    private static HashMap<String, Account> onlineAccounts = new HashMap<>();

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

    public static HashMap<String, Account> getOnlineAccounts() {
        return onlineAccounts;
    }

    public static void setOnlineAccounts(HashMap<String, Account> onlineAccounts) {
        Main.onlineAccounts = onlineAccounts;
    }

    public static void addToOnlineAccounts(String outhToken,Account account){
        onlineAccounts.put(outhToken,account);
        Game.getInstance().addToOnlineAccounts(account);
    }

    public static void removeFromOnlineAccounts(String outhToken){
        Game.getInstance().removeFromOnlineAccounts(onlineAccounts.get(outhToken));
        onlineAccounts.remove(outhToken);
    }
}
