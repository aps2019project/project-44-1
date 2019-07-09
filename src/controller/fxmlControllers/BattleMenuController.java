package controller.fxmlControllers;

import Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import models.Game;
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
            single.setOnAction(actionEvent -> loadSingleMenu());
            multi.setOnAction(actionEvent -> loadMultiMenu());
            back.setOnAction(actionEvent -> loadMainMenu());


    }

    private void loadMainMenu(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/mainMenu.fxml"));
        try {
            Main.getStage().getScene().setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMultiMenu(){
        Request request = new Request(Environment.BATTLE);
        request.setRequestType(RequestType.ENTER_WAIT_PAGE_FOR_SECOND_PLAYER);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/MultiMenu.fxml"));
        try {
            Main.getStage().getScene().setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSingleMenu() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/singleMenu.fxml"));
        try {
            Main.getStage().getScene().setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
