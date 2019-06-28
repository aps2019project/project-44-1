package controller.fxmlControllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Game;

import java.net.URL;
import java.util.ResourceBundle;

public class SingleMenuController implements Initializable {
    public ImageView first;
    public ImageView second;
    public ImageView third;
    public Label fLabel;
    public Label sLabel;
    public Label tLabel;
    public Button back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        first.setImage(new Image("view/images/general_portrait_image_f1.png"));
        second.setImage(new Image("view/images/general_portrait_image_f2.png"));
        third.setImage(new Image("view/images/general_portrait_image_f3.png"));
        fLabel.setText("DeathMatch\ndivsefid");
        sLabel.setText("SaveFlag\nzahhak");
        tLabel.setText("CaptureFlag\narashv");
        first.setOnMouseClicked(mouseEvent -> Game.getInstance().loadPage(first, ""));      // TODO: 6/29/2019 after designing map
        second.setOnMouseClicked(mouseEvent -> Game.getInstance().loadPage(first, ""));
        third.setOnMouseClicked(mouseEvent -> Game.getInstance().loadPage(first, ""));
        back.setOnAction(actionEvent -> Game.getInstance().loadPage(fLabel, "/view/fxmls/battleMenu.fxml"));
    }

}
