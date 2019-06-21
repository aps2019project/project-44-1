package controller.fxmlControllers;

import Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import models.Collection;
import models.Placeable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CollectionController implements Initializable {
    public Button createDeckButton;
    public Button setMainDeckButton;
    public Button removeFromDeckButton;
    public Button deleteDeckButton;
    public ScrollPane deckCardsScrollPane;
    public ScrollPane allCardsScrollPane;
    public Button backButton;
    public Button addCardToDeckButton;
    public MenuButton decks;
    private static Collection collection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = null;
        try {
            image = new Image(new FileInputStream("src/view/images/general_f1third.png"), 150, 200, false, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FlowPane allCardsFlowPane = new FlowPane();
        FlowPane deckCardsFlowPane = new FlowPane();
        allCardsScrollPane.setPannable(true);
        allCardsScrollPane.setContent(allCardsFlowPane);

        for (Placeable card : collection.getCollectionCards()) {
            VBox vBox = new VBox();
            ImageView imageView = new ImageView(image);
            Label name = new Label(card.getName());
            vBox.getChildren().addAll(imageView,name);
            allCardsFlowPane.getChildren().add(vBox);
        }
    }


    public static void setCollection(Collection collection1) {
        collection = collection1;
    }

    public void backAction() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/mainMenu.fxml"));
        try {
            Main.getStage().getScene().setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
