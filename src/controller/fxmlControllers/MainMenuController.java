package controller.fxmlControllers;

import Main.Main;
import controller.logicController.AccountController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import models.Game;

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
//        accountController.enterCollection();
        try {
            CollectionFxmlController.setCollection(accountController.getAccount().getCollection());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/collectionPage.fxml"));
            Main.getStage().getScene().setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goToShop() {
        accountController.enterShop();
        Game.getInstance().loadPage(exitButton, "/view/fxmls/shop.fxml");
    }

    private void save() {
        accountController.save();
    }

    private void exit() {
        System.exit(0);
    }

    private void goToBattle() {
//        accountController.enterBattle();
        Game.getInstance().loadPage(exitButton,"/view/fxmls/battleMenu.fxml");
    }

    private void logout() {
        accountController.logout(exitButton);
    }

    private void showMatchHistories() {
        Game.getInstance().loadPage(exitButton, "/view/fxmls/matchHistories.fxml");
    }

}
