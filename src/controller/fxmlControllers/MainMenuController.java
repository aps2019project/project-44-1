package controller.fxmlControllers;

import Main.Main;
import controller.AccountController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import models.Account;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    public Button battleButton;
    public Button collectionButton;
    public Button shopButton;
    public Button saveButton;
    public Button logoutButton;
    public Button exitButton;
    public Button historyButton;
    private AccountController accountController = AccountController.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shopButton.setOnAction(event -> goToShop());
        collectionButton.setOnAction(event -> goToCollection());
        exitButton.setOnAction(actionEvent -> exit());
        logoutButton.setOnAction(actionEvent -> logout());
        saveButton.setOnAction(actionEvent -> save());
        battleButton.setOnAction(actionEvent -> goToBattle());
        historyButton.setOnAction(actionEvent -> showMatchHistories());
    }

    private void goToCollection() {
        accountController.enterCollection();
        Main.getStage().getScene().setRoot(Main.getCollectionPage());
    }

    private void goToShop() {
        accountController.enterShop();
        Main.getStage().getScene().setRoot(Main.getShopPage());
    }

    private void save() {
        accountController.save();
    }

    private void exit() {
        System.exit(0);
    }

    private void goToBattle() {
//        accountController.enterBattle();
    }

    private void logout() {
        accountController.logout();
    }

    private void showMatchHistories() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/matchHistories.fxml"));
        try {
            exitButton.getScene().setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
