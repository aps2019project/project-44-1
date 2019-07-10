package controller.fxmlControllers;

import Main.Main;
import client.ResponseHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import server.Environment;
import server.Request;
import server.RequestType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BattleMenuController implements Initializable {
    public Button single;
    public Button multi;
    public Button back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        single.setOnAction(actionEvent -> MainMenuController.loadPage("/view/fxmls/singleMenu.fxml"));
        multi.setOnAction(actionEvent -> MainMenuController.loadPage("/view/fxmls/MultiMenu.fxml"));
        back.setOnAction(actionEvent -> loadMainMenu());
    }

    private void loadMainMenu(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/mainMenu.fxml"));
        try {
            Main.getStage().getScene().setRoot(loader.load());
            ResponseHandler.getInstance().setMainMenuController(loader.getController());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
