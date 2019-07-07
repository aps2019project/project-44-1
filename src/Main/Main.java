package Main;

import client.RequestSender;
import client.ResponseHandler;
import controller.fxmlControllers.LoginPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import server.Environment;
import server.Request;
import server.RequestType;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Main extends Application {
    private static Parent mainMenu;
    private static Stage stage;
    private static LoginPageController loginPageController;
    private static ResponseHandler clientResponseHandler;
    private static String token;


    static {
        try {
            FXMLLoader.load(Main.class.getResource("/view/fxmls/loginPage.fxml"));
            mainMenu = FXMLLoader.load(Main.class.getResource("/view/fxmls/mainMenu.fxml"));
            FXMLLoader.load(Main.class.getResource("/view/fxmls/shop.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //---------------------------------------------------- windows are loaded

    public static void main(String[] args) {
        play();
        connectToServer();
        launch(args);
        System.exit(0);
    }

    private static void connectToServer() {
        try {
            InetAddress ip = InetAddress.getByName("localhost");
            Socket socket = new Socket(ip, 8000);
            ResponseHandler responseHandler = ResponseHandler.getInstance();
            ResponseHandler.getInstance().setJsonStreamParser(socket.getInputStream());
            clientResponseHandler = responseHandler;
            RequestSender.getInstance().setBufferedWriter(socket.getOutputStream());
            responseHandler.setDaemon(true);
            responseHandler.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxmls/loginPage.fxml"));
        Parent root = fxmlLoader.load();
        loginPageController = fxmlLoader.getController();
        graphicStarter(primaryStage, root);
        primaryStage.setOnCloseRequest(act -> closeConnection());
        primaryStage.show();
    }

    public static void closeConnection() {
        Request request = new Request(Environment.LOGIN_PAGE);
        request.setRequestType(RequestType.CLOSE_CONNECTION);
        RequestSender.getInstance().sendRequest(request);
        try {
            RequestSender.getInstance().closeBufferedWriter();
            clientResponseHandler.interrupt();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void graphicStarter(Stage primaryStage, Parent root) {
        Main.setStage(primaryStage);
        primaryStage.setTitle("DUELYST");
        Scene scene = new Scene(root, 850, 500);
        primaryStage.getIcons().add(new Image("/view/images/icon_gold.png"));
        scene.getStylesheets().add("/view/styleSheets/login.css");
        scene.getStylesheets().add("/view/styleSheets/mainMenu.css");
        primaryStage.setScene(scene);
    }

    public static Stage getStage() {
        return stage;
    }

    private static void setStage(Stage stage) {
        Main.stage = stage;
    }

    public static Parent getMainMenu() {
        return mainMenu;
    }

    private static void play() {
        Thread thread = new Thread(() -> {
            try {
                InputStream audioSrc = new FileInputStream("src\\view\\mainmenu_v2c_looping.wav");
                InputStream bufferedIn = new BufferedInputStream(audioSrc);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
                do {
                    Thread.sleep(15);
                } while (clip.isRunning());
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.setName("player");
        thread.start();
    }

    public static LoginPageController getLoginPageController() {
        return loginPageController;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Main.token = token;
    }

}
