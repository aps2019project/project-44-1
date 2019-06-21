package controller.fxmlControllers;

import Main.Main;
import controller.AccountController;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    public Button battleButton;
    public Button collectionButton;
    public Button shopButton;
    public Button saveButton;
    public Button logoutButton;
    public Button exitButton;
//    public Button historyButton;
    private AccountController accountController = AccountController.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shopButton.setOnAction(event -> goToShop());
        collectionButton.setOnAction(event -> goToCollection());
        exitButton.setOnAction(actionEvent -> exit());
        logoutButton.setOnAction(actionEvent -> logout());
        saveButton.setOnAction(actionEvent -> save());
        battleButton.setOnAction(actionEvent -> goToBattle());
        // FIXME: 6/21/2019 matchHistory
//        historyButton.setOnAction(actionEvent -> showMatchHistories());
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
    }

}
