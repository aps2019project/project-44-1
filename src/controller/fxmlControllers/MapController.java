package controller.fxmlControllers;

import controller.logicController.BattleController;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import models.Card;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
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
    public Button send;
    public VBox chatBox;
    public TextField message;
    public TitledPane gChat;
    private boolean playing = true;
    private BattleController battleController = BattleController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deletePastRecord();
        screenShot();
        initializeButtons();
        setImages();
        cheat.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER))
                applyCheat(cheat.getText());
        });
    }

    private void deletePastRecord() {
        File folder = new File("src\\view\\record");
        try {
            if (folder.listFiles() != null) {
                for (File f : Objects.requireNonNull(folder.listFiles())) {
                    if (!f.delete())
                        throw new IOException();
                }
            } else folder.mkdir();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void initializeButtons() {
        exit.setOnAction(actionEvent -> {
            playing = false;
            battleController.gameHistory();
            MainMenuController.loadPage("/view/fxmls/mainMenu.fxml");
        });
        save.setOnAction(actionEvent -> battleController.save());
        graveyard.setOnAction(actionEvent -> battleController.enterGraveyard());
        endTurn.setOnAction(actionEvent -> battleController.endTurn());
    }

    private void setImages() {
        File folder = new File("src/view/images/generalPortrait");
        File[] listOfFiles = folder.listFiles();
        Random random = new Random();
        int x = random.nextInt(5);
        try {
            firstPlayer.setImage(new Image(Objects.requireNonNull(listOfFiles)[x].getPath().substring(4)));
            x = random.nextInt(5);
            secondPlayer.setImage(new Image(listOfFiles[x].getPath().substring(4)));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(battleController.getBattle().getCurrentPlayer().getHand()));
        System.out.println(battleController.getBattle().getCurrentPlayer().getNextCardInHand());
//        setHandImages(battleController.getBattle().getCurrentPlayer().getHand(),
//                battleController.getBattle().getCurrentPlayer().getNextCardInHand());
    }

    private void setHandImages(Card[] cards, Card next) {
        nextInHand.setImage(new Image(next.getPath().substring(4)));
        first.setImage(new Image(cards[0].getPath().substring(4)));
        second.setImage(new Image(cards[1].getPath().substring(4)));
        third.setImage(new Image(cards[2].getPath().substring(4)));
        fourth.setImage(new Image(cards[3].getPath().substring(4)));
        fifth.setImage(new Image(cards[4].getPath().substring(4)));
    }

    private void screenShot() {
        Thread thread = new Thread(() -> {
            int index = 0;
            Dimension d = new Dimension();
            d.setSize(map.getWidth(), map.getHeight());
            Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            while (playing) {
                try {
                    Robot r = new Robot();
                    BufferedImage Image = r.createScreenCapture(capture);
                    ImageIO.write(Image, "jpg", new File("src\\view\\record\\Shot"
                            + (index++) + ".jpg"));
                    r.setAutoDelay(100);
                } catch (AWTException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        thread.setName("recorder");
        thread.start();
    }

    public synchronized void setPlaying(boolean playing) {
        this.playing = playing;
    }

    private void applyCheat(String s) {

    }

}
