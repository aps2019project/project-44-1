package server;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Account;
import models.Game;
import models.Placeable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {

    private static ArrayList<Socket> sockets = new ArrayList<>();
    private static HashMap<String, Account> onlineAccounts = new HashMap<>();

    public static void main(String[] args) throws IOException {
        int port;
        FileReader reader = new FileReader("src/server/config");
        BufferedReader bufferedReader = new BufferedReader(reader);
        port = Integer.parseInt(bufferedReader.readLine().split(":")[1]);
        bufferedReader.close();
        reader.close();
        ServerSocket server = new ServerSocket(port);
        Socket socket;
        System.out.println("server is running...");
        OpponentFinder opponentFinder = new OpponentFinder();
        opponentFinder.start();
//        launch(args);
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

    public static void addToOnlineAccounts(String outhToken, Account account) {
        onlineAccounts.put(outhToken, account);
        Game.getInstance().addToOnlineAccounts(account);
    }

    public static void removeFromOnlineAccounts(String outhToken) {
        Game.getInstance().removeFromOnlineAccounts(onlineAccounts.get(outhToken));
        onlineAccounts.remove(outhToken);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/server/shopInServer.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root));

        primaryStage.show();
    }
}
