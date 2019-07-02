package controller.fxmlControllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
    public TitledPane result;
    public Label message;
    public AnchorPane anchorPane;
    private int index = 0;
    private File[] listOfFiles;
    private Timeline animation;
    private boolean win;
    private int reward;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showMessage();
        animate();
        back.setOnAction(actionEvent -> Game.getInstance().loadPage(back, "view/fxmls/mainMenu.fxml"));
        play.setOnAction(actionEvent -> player());
    }

    private void animate() {
        EventHandler<ActionEvent> eventHandler = e -> view.setImage(getImage());
        animation = new Timeline(new KeyFrame(Duration.millis(100), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        File folder = new File("src\\view\\record");
        listOfFiles = folder.listFiles();
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
        if (index >= listOfFiles.length) {
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

    private void showMessage() {
        if (win) {
            anchorPane.setBackground(new Background(new BackgroundImage(new
                    Image("view/images/tournament_winner@2x.png"), BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            message.setText("your reward : " + reward);
        } else {
            anchorPane.setBackground(new Background(new BackgroundImage(new
                    Image("view\\images\\b8438acd7759c6c582630658b0898ec3.jpg"), BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            message.setVisible(false);
        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                result.setExpanded(false);
            }
        }, 7000);
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

}
