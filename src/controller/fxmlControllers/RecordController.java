package controller.fxmlControllers;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import models.Game;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class RecordController implements Initializable {
    public Button back;
    public Button play;
    public ImageView view;
    public AnchorPane pane;
    private int index = 0;
    private File[] listOfFiles;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        view.setY(50);
        view.setY(50);
        view.setFitWidth(500);
        view.setFitHeight(500);
        File folder = new File("src\\view\\images");
        listOfFiles = folder.listFiles();
        back.setOnAction(actionEvent -> Game.getInstance().loadPage(back, "view/fxmls/mainMenu.fxml"));
        play.setOnAction(actionEvent -> player());
    }

    private void player() {
        EventHandler<ActionEvent> eventHandler = e -> {
            pane.getChildren().remove(view);
            pane.getChildren().add(getImage());
        };
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(30), eventHandler));
        animation.setCycleCount(2);
        animation.play();

    }

    private ImageView getImage() {
        index++;
        return new ImageView(new Image(listOfFiles[index].getPath().substring(4)));
    }

}
