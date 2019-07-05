package controller.fxmlControllers;

import controller.logicController.AccountController;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Game;

import java.net.URL;
import java.util.ResourceBundle;

public class SingleMenuController extends MultiMenuController implements Initializable {
    public ImageView first;
    public ImageView second;
    public ImageView third;
    public Label fLabel;
    public Label sLabel;
    public Label tLabel;
    public Button back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        first.setImage(new Image("view/images/generalPortrait/general_portrait_image_f1.png"));
        second.setImage(new Image("view/images/generalPortrait/general_portrait_image_f2.png"));
        third.setImage(new Image("view/images/generalPortrait/general_portrait_image_f3.png"));
        fLabel.setText("DeathMatch\ndivsefid");
        sLabel.setText("SaveFlag\nzahhak");
        tLabel.setText("CaptureFlag\narash");
        first.setOnMouseClicked(mouseEvent -> enterBattle(AccountController.SINGLE1));
        second.setOnMouseClicked(mouseEvent -> enterBattle(AccountController.SINGLE2));
        third.setOnMouseClicked(mouseEvent -> enterBattle(AccountController.SINGLE3));
        back.setOnAction(actionEvent -> Game.getInstance().loadPage(fLabel, "/view/fxmls/battleMenu.fxml"));
    }

}