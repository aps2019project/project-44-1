package controller.fxmlControllers;

import client.RequestSender;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import models.Account;
import server.Environment;
import server.Request;
import server.RequestType;

import java.net.URL;
import java.util.ResourceBundle;

public class BattleMenuController implements Initializable {
    public Button single;
    public Button multi;
    public Button back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        single.setOnAction(actionEvent -> MainMenuController.loadPage("/view/fxmls/singleMenu.fxml"));
        multi.setOnAction(actionEvent -> loadMultiMenu());
        back.setOnAction(actionEvent -> MainMenuController.loadPage("/view/fxmls/mainMenu.fxml"));
    }

    private void loadMultiMenu(){
        Request request = new Request(Environment.BATTLE);
        request.setRequestType(RequestType.ENTER_WAIT_PAGE_FOR_SECOND_PLAYER);
        RequestSender.getInstance().sendRequest(request);
    }
}
