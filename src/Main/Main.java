package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Parent mainMenu;
    private static Stage stage;


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
        launch(args);
        System.exit(0);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/fxmls/loginPage.fxml"));
        Main.setStage(primaryStage);
        primaryStage.setTitle("DUELYST");
        Scene scene = new Scene(root, 850, 500);
        primaryStage.getIcons().add(new Image("/view/images/icon_gold.png"));
        scene.getStylesheets().add("/view/styleSheets/login.css");
        scene.getStylesheets().add("/view/styleSheets/mainMenu.css");
        primaryStage.setScene(scene);
        primaryStage.show();
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

}
