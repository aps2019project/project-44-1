package controller.fxmlControllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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
        back.setOnAction(actionEvent -> MainMenuController.loadPage("/view/fxmls/mainMenu.fxml"));
    }

}
