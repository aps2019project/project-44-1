package controller.fxmlControllers;

import Main.Main;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shopButton.setOnAction(event -> Main.getStage().getScene().setRoot(Main.getShopPage()));
    }

}
