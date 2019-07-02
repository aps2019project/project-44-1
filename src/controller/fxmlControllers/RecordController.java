package controller.fxmlControllers;

import javafx.animation.Animation;
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

public class RecordController implements Initializable {
    public Button back;
    public Button play;
    public ImageView view;
    public AnchorPane pane;
    private int index = 0;
    private File[] listOfFiles;
    private Timeline animation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EventHandler<ActionEvent> eventHandler = e -> view.setImage(getImage());
        animation = new Timeline(new KeyFrame(Duration.millis(100), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        File folder = new File("src\\view\\images");
        listOfFiles = folder.listFiles();
        back.setOnAction(actionEvent -> Game.getInstance().loadPage(back, "view/fxmls/mainMenu.fxml"));
        play.setOnAction(actionEvent -> player());
    }

    private void player() {
        if (animation.getStatus().equals(Animation.Status.RUNNING))
            animation.pause();
        else {
            animation.play();
        }
    }

    private Image getImage() {
        index++;
        if (index >= 16) {
            animation.stop();
            index = 0;
        }
        Image i = null;
        try {

            i = new Image(listOfFiles[index].getPath().substring(4));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println(index);
        }
        return i;
    }

}
