package controller.fxmlControllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import models.Game;

import java.net.URL;
import java.util.ResourceBundle;

public class BattleMenuController implements Initializable {
    public Button single;
    public Button multi;
    public Button back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        single.setOnAction(actionEvent -> Game.getInstance().loadPage(single, "/view/fxmls/singleMenu.fxml"));
        multi.setOnAction(actionEvent -> Game.getInstance().loadPage(multi, "/view/fxmls/MultiMenu.fxml"));
        back.setOnAction(actionEvent -> Game.getInstance().loadPage(single, "/view/fxmls/mainMenu.fxml"));
    }

}
