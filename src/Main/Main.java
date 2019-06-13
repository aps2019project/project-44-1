package Main;

import controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Parent loginPage;
    private static Parent mainMenu;
    private static Parent shopPage;
    private static Stage stage;


    static {
        try {
            loginPage = FXMLLoader.load(Main.class.getResource("/view/fxmls/loginPage.fxml"));
            mainMenu = FXMLLoader.load(Main.class.getResource("/view/fxmls/mainMenu.fxml"));
            shopPage = FXMLLoader.load(Main.class.getResource("/view/fxmls/shop.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
        GameController.getInstance().main();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/fxmls/loginPage.fxml"));
        Main.setStage(primaryStage);
        primaryStage.setTitle("DUELYST");
        Scene scene = new Scene(root, 850, 500);
        scene.getStylesheets().add("/view/styleSheets/login.css");
        scene.getStylesheets().add("/view/styleSheets/mainMenu.css");
        scene.getStylesheets().add("/view/styleSheets/shop.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Parent getLoginPage() {
        return loginPage;
    }

    public static void setLoginPage(Parent loginPage) {
        Main.loginPage = loginPage;
    }

    public static Parent getMainMenu() {
        return mainMenu;
    }

    public static void setMainMenu(Parent mainMenu) {
        Main.mainMenu = mainMenu;
    }

    public static Stage getStage() {
        return stage;
    }

    private static void setStage(Stage stage) {
        Main.stage = stage;
    }

    public static Parent getShopPage() {
        return shopPage;
    }

    public static void setShopPage(Parent shopPage) {
        Main.shopPage = shopPage;
    }

}
