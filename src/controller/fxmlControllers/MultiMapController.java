package controller.fxmlControllers;

import client.CardBuilder;
import client.RequestSender;
import controller.logicController.BattleController;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.*;
import server.Environment;
import server.Request;
import server.RequestType;

import java.net.URL;
import java.util.ResourceBundle;

public class MultiMapController extends MapController implements Initializable {

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
        battleController = BattleController.getInstance();
        super.initialize(url, resourceBundle);
        showCardsInMap();
        buildMap();
    }

    private void buildMap() {
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
        select(event);
        move(targetColumn, targetRow);
    }

    private void move(int targetColumn, int targetRow) {
        Card card = logicMap.getCells()[selectedRow][selectedColumn].getCard();
        Card targetCard = logicMap.getCells()[targetRow][targetColumn].getCard();
        if (card != null && card.getInGameID().split("_")[0].equals(player.getName()) &&
                (cardBuilder.getCard(card.getName()) instanceof Hero || cardBuilder.getCard(card.getName()) instanceof Minion)) {
            if (targetCard == null || cardBuilder.getCard(card.getName()) instanceof Item) {
                moveCard(targetRow, targetColumn);
            }
        }
    }

    private void moveCard(int targetRow, int targetColumn) {
        Request request = new Request(Environment.BATTLE);
        request.setRequestType(RequestType.MOVE_CARD);
        request.setCoordinate(selectedColumn, selectedRow, targetColumn, targetRow);
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

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        MultiMapController.player = player;
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

    private boolean isSelected() {
        return selectedRow != -1 || selectedColumn != -1;
    }

    public void updateMap() {
        showCardsInMap();
    }

}
