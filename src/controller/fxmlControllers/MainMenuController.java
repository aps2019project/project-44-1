package controller.fxmlControllers;

import Main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import models.Account;
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
    private static Account account;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shopButton.setOnAction(event -> Main.getStage().getScene().setRoot(Main.getShopPage()));
        collectionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    CollectionController.setCollection(account.getCollection());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/collectionPage.fxml"));
                    Main.getStage().getScene().setRoot(loader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static void setAccount(Account account1) {
        account = account1;
    }
}
