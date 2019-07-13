package controller.fxmlControllers;

import client.CardBuilder;
import client.RequestSender;
import controller.logicController.BattleController;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.*;
import server.Environment;
import server.Request;
import server.RequestType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class MultiMapController implements Initializable {

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
    private BattleController battleController;
    private static Player player;
    private static Map logicMap;
    private CardBuilder cardBuilder = new CardBuilder();
    private static String secondPlayerName;
    private int selectedRow;
    private int selectedColumn;
    private HBox selectedCell;
    private HBox[][] hBoxes = new HBox[5][9];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        battleController = BattleController.getInstance();
        deletePastRecord();
        screenShot();
        initializeButtons();
        setImages();
        cheat.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER))
                applyCheat(cheat.getText());
        });
        send.setOnAction(actionEvent -> chat());
        showCardsInMap();
        buildMap();
    }

    private void buildMap() {
//        double width = map.getWidth() / 9;
//        double height = map.getHeight() / 5;
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 9; j++) {
//                hBoxes[i][j] = new HBox();
//                hBoxes[i][j].setMinSize(width, height);
//                map.add(hBoxes[i][j], j, i);
//                hBoxes[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent event) {
//                        if (event.isPrimaryButtonDown())
////                            setSelectedCell(event.ge);
//                        hBoxes[selectedRow][selectedColumn].setStyle("-fx-background-color: #4b6680");
//                    }
//                });
//            }
//        }
        map.setOnMouseClicked(event -> {
            if (!isSelected()) {
                select(event);
            }
            if (isSelected()) {
                moveOrAttack(event);
            }
            if (event.isSecondaryButtonDown()) {
                selectedRow = -1;
                selectedColumn = -1;
            }

        });


    }

    private void moveOrAttack(MouseEvent event) {
        int targetColumn = 0;
        int targetRow = 0;
        if (event.isPrimaryButtonDown()) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    if (map.getCellBounds(j, i).contains(event.getX(), event.getY(), event.getZ())) {
                        targetColumn = j;
                        targetRow = i;
                    }
                }
            }
        }
        Card card = logicMap.getCells()[selectedRow][selectedColumn].getCard();
        Card targetCard = logicMap.getCells()[targetRow][targetColumn].getCard();
        if (card != null && card.getInGameID().split("_")[0].equals(player.getName()) &&
                (cardBuilder.getCard(card.getName()) instanceof Hero || cardBuilder.getCard(card.getName()) instanceof Minion)) {
            if (targetCard == null || cardBuilder.getCard(card.getName())instanceof Item){
                moveCard(targetRow,targetColumn);
            }
        }
    }

    private void moveCard(int targetRow, int targetColumn) {
        Request request = new Request(Environment.BATTLE);
        request.setRequestType(RequestType.MOVE_CARD);
        request.setCoordinate(selectedColumn,selectedRow,targetColumn,targetRow);
        RequestSender.getInstance().sendRequest(request);
    }

    private void select(MouseEvent event) {
        if (event.isPrimaryButtonDown()) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    if (map.getCellBounds(j, i).contains(event.getX(), event.getY(), event.getZ())) {
                        selectedColumn = j;
                        selectedRow = i;
                    }
                }
            }
        }
    }

    private void showCardsInMap() {
        map.getChildren().removeAll();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                Card card = logicMap.getCells()[i][j].getCard();
                if (card != null && cardBuilder.getCard(card.getName()).getPath() != null) {
                    ImageView imageView = new ImageView(new Image(cardBuilder.getCard(card.getName()).getPath()));
                    if (card.getInGameID().split("_")[0].equals(secondPlayerName))
                        imageView.setScaleX(-1);
                    map.add(imageView, j, i);
                }
            }
        }
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
            MainMenuController.loadPage("/view/fxmls/record.fxml");
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
        System.out.println(Arrays.toString(player.getHand()));
        System.out.println(player.getNextCardInHand());
        setHandImages(player.getHand(),
                player.getNextCardInHand());
    }

    private void setHandImages(Card[] cards, Card next) {
        nextInHand.setImage(new Image(cardBuilder.getCard(next.getName()).getPath()));
        first.setImage(new Image(cardBuilder.getCard(cards[0].getName()).getPath()));
        second.setImage(new Image(cardBuilder.getCard(cards[1].getName()).getPath()));
        third.setImage(new Image(cardBuilder.getCard(cards[2].getName()).getPath()));
        fourth.setImage(new Image(cardBuilder.getCard(cards[3].getName()).getPath()));
        fifth.setImage(new Image(cardBuilder.getCard(cards[4].getName()).getPath()));
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
                    r.setAutoDelay(500);
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

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        MultiMapController.player = player;
    }

    public static Map getLogicMap() {
        return logicMap;
    }

    public static void setLogicMap(Map logicMap) {
        MultiMapController.logicMap = logicMap;
    }

    public static String getSecondPlayerName() {
        return secondPlayerName;
    }

    public static void setSecondPlayerName(String secondPlayerName) {
        MultiMapController.secondPlayerName = secondPlayerName;
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }

    public int getSelectedColumn() {
        return selectedColumn;
    }

    public void setSelectedCell(int selectedRow, int selectedColumn) {
        this.selectedColumn = selectedColumn;
        this.selectedRow = selectedRow;
    }

    private void applyCheat(String s) {

    }

    private void chat() {
        String text = message.getText();
        if (text.equals(""))
            return;
        Request request = new Request(Environment.MAP);
        request.setRequestType(RequestType.CHAT);
        request.setMessage(text);
        RequestSender.getInstance().sendRequest(request);
    }

    private boolean isSelected() {
        return selectedRow != -1 || selectedColumn != -1;
    }


    public void updateMap() {
        showCardsInMap();
    }
}
