package controller.fxmlControllers;

import Main.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import models.Card;
import models.Collection;
import models.Deck;
import models.Placeable;
import view.fxmls.wrapperClasses.CardContainer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class CollectionController implements Initializable {
    public Button createDeckButton;
    public Button setMainDeckButton;
    public Button removeFromDeckButton;
    public Button deleteDeckButton;
    public FlowPane deckCardsFlowPane;
    public FlowPane allCardsFlowPane;
    public Button backButton;
    public Button addCardToDeckButton;
    public ComboBox<String> decks;
    private static Collection collection;
    public ScrollPane deckCardsScrollPane;
    private ArrayList<CardContainer> deckCards;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addCreatedDecksToComboBox();
        showSelectedDeckCards();
        addCollectionCards();
        deleteDeckBtnOnAction();
        removeFromDeckButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (decks.getValue()==null)
                    return;
                Deck selectedDeck = collection.getDeck(decks.getValue());

                for (CardContainer card:deckCards){
                    if (card.getCheckBox().isSelected()){
                        selectedDeck.removeFromDeck(card.getNameLabel().getText());
                        deckCardsFlowPane.getChildren().remove(card.getAnchorPane());
                    }
                }
                deckCards.removeIf(cardContainer -> cardContainer.getCheckBox().isSelected());
            }
        });
    }

    private void deleteDeckBtnOnAction() {
        deleteDeckButton.setOnAction(event -> {
            if (decks.getValue() == null)
                return;
            collection.deleteDeck(decks.getValue());
            decks.getItems().remove(decks.getValue());
            deckCardsFlowPane.getChildren().clear();
            decks.setValue(null);
        });
    }

    private void addCollectionCards() {
        for (Placeable card : collection.getCollectionCards()) {
            CardContainer cardContainer;
            if (card == null)
                continue;
            if (card instanceof Card)
                cardContainer = new CardContainer((Card) card);
            else
                cardContainer = new CardContainer(card);
            allCardsFlowPane.getChildren().add(cardContainer.getAnchorPane());
        }
    }

    private void addCreatedDecksToComboBox() {
        for (Deck deck : collection.getSortedDecks()) {
            decks.getItems().add(deck.getName());
        }
    }

    private void showSelectedDeckCards() {
        EventHandler<ActionEvent> eventEventHandler = event -> {
            if (decks.getValue() != null) {
                deckCards = new ArrayList<>();
                for (Placeable card : collection.getDeck(decks.getValue()).getDeckCards()) {
                    CardContainer cardContainer;
                    if (card == null)
                        continue;
                    if (card instanceof Card)
                        cardContainer = new CardContainer((Card) card);
                    else
                        cardContainer = new CardContainer(card);
                    deckCardsFlowPane.getChildren().add(cardContainer.getAnchorPane());
                    deckCards.add(cardContainer);
                }
            }
        };

        decks.setOnAction(eventEventHandler);
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
