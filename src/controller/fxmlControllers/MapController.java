package controller.fxmlControllers;

import controller.logicController.BattleController;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import models.Card;
import models.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MapController implements Initializable {

    public GridPane map;
    public TextField cheat;
    public Button exit;
    public Button save;
    public Button graveyard;
    public Button endTurn;
    public ImageView first;
    public ImageView second;
    public ImageView third;
    public ImageView fourth;
    public ImageView fifth;
    public ImageView nextInHand;
    public ImageView firstPlayer;
    public ImageView secondPlayer;
    private boolean playing = true;
    private BattleController battleController = BattleController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        screenShot();
        initializeButtons();
        setImages();
        cheat.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER))
                applyCheat(cheat.getText());
        });
        deletePastRecord();
    }

    private void deletePastRecord() {
        File folder = new File("src\\view\\record");
        try {
            if (folder.listFiles() != null) {
                for (File f : folder.listFiles()) {
                    if (!f.delete())
                        throw new IOException();
                }
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void initializeButtons() {
        exit.setOnAction(actionEvent -> {
            playing = false;
            battleController.gameHistory();
            Game.getInstance().loadPage(exit, "/view/fxmls/mainMenu.fxml");
        });
        save.setOnAction(actionEvent -> battleController.save());
        graveyard.setOnAction(actionEvent -> battleController.enterGraveyard());
        endTurn.setOnAction(actionEvent -> battleController.endTurn());
    }

    private void setImages() {
        File folder = new File("src\\view\\generalPortrait");
        File[] listOfFiles = folder.listFiles();
        Random random = new Random();
        int x = random.nextInt(5);
        try {
            firstPlayer.setImage(new Image(listOfFiles[x].getPath()));
            x = random.nextInt(5);
            secondPlayer.setImage(new Image(listOfFiles[x].getPath()));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        setHandImages(battleController.getBattle().getCurrentPlayer().getHand(),
                battleController.getBattle().getCurrentPlayer().getNextCardInHand());
    }

    private void setHandImages(Card[] cards, Card next) {
        nextInHand.setImage(new Image(next.getPath()));
        first.setImage(new Image(cards[0].getPath()));
        second.setImage(new Image(cards[1].getPath()));
        third.setImage(new Image(cards[2].getPath()));
        fourth.setImage(new Image(cards[3].getPath()));
        fifth.setImage(new Image(cards[4].getPath()));
    }

    public void screenShot() {
        Thread thread = new Thread(() -> {
            int index = 0;
            while (playing) {
                try {
                    Robot r = new Robot();
                    Dimension d = new Dimension();
                    d.setSize(map.getWidth(), map.getHeight());
                    Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                    BufferedImage Image = r.createScreenCapture(capture);
                    ImageIO.write(Image, "jpg", new File("src\\view\\record\\Shot" + (index++) + ".jpg"));
                    r.setAutoDelay(100);
                } catch (AWTException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        thread.setName("recorder");
        thread.setDaemon(true);
        thread.start();
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    private void applyCheat(String s) {

    }

}
