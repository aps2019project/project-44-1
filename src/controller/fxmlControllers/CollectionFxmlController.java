package controller.fxmlControllers;

import Main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import models.*;
import view.fxmls.wrapperClasses.CardContainer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CollectionFxmlController implements Initializable {
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
    private ArrayList<CardContainer> collectionCards;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addCreatedDecksToComboBox();
        showSelectedDeckCards();
        addCollectionCards();
        deleteDeckBtnOnAction();
        removeCardFromDeckBtn();
        selectMainDeckBtn();
        addCardToDeckBtn();
    }

    private void addCardToDeckBtn() {
        addCardToDeckButton.setOnAction(event -> {
            if (decks.getValue() == null || !checkSelectedCards())
                return;
            for (CardContainer cardContainer : collectionCards) {
                if (cardContainer.getCheckBox().isSelected()) {
                    collection.getDeck(decks.getValue()).addToDeck(cardContainer.getCard());
                    deckCards.add(cardContainer);
                }
            }
        });
    }

    private void selectMainDeckBtn() {
        setMainDeckButton.setOnAction(event -> {
            if (decks.getValue() == null)
                return;
            collection.selectMainDeck(decks.getValue());
        });
    }

    private void removeCardFromDeckBtn() {
        removeFromDeckButton.setOnAction(event -> {
            if (decks.getValue() == null)
                return;
            Deck selectedDeck = collection.getDeck(decks.getValue());

            for (CardContainer card : deckCards) {
                if (card.getCheckBox().isSelected()) {
                    selectedDeck.removeFromDeck(card.getCard());
                    deckCardsFlowPane.getChildren().remove(card.getAnchorPane());
                }
            }
            deckCards.removeIf(cardContainer -> cardContainer.getCheckBox().isSelected());
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
        collectionCards = new ArrayList<>();
        for (Placeable card : collection.getCollectionCards()) {
            setCardContainer(card, allCardsFlowPane, collectionCards);
        }
    }

    private void setCardContainer(Placeable card, FlowPane allCardsFlowPane, ArrayList<CardContainer> collectionCards) {
        CardContainer cardContainer;
        if (card == null)
            return;
        if (card instanceof Card)
            cardContainer = new CardContainer((Card) card);
        else
            cardContainer = new CardContainer(card);
        allCardsFlowPane.getChildren().add(cardContainer.getAnchorPane());
        collectionCards.add(cardContainer);
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
                    setCardContainer(card, deckCardsFlowPane, deckCards);
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

    private boolean checkSelectedCards() {
        int heroCounter = 0;
        int itemCounter = 0;
        int normalCardCounter = 0;
        for (CardContainer cardContainer : collectionCards) {
            if (cardContainer.getCheckBox().isSelected()) {
                if (cardContainer.getCard() instanceof Hero)
                    heroCounter++;
                if (cardContainer.getCard() instanceof Item)
                    itemCounter++;
                if (cardContainer.getCard() instanceof Minion || cardContainer.getCard() instanceof Spell)
                    normalCardCounter++;
            }
        }
        if (heroCounter > 1 || (heroCounter == 1 && !collection.canAddHero(collection.getDeck(decks.getValue())))) {
            // dialogBox to say "can not add more than one hero to deck"
            return false;
        }
        if (itemCounter > 1 || (itemCounter == 1 && !collection.canAddItem(collection.getDeck(decks.getValue())))) {
            // dialogBox to say "can not add more than one item to deck"
            return false;
        }
        if (collection.getDeck(decks.getValue()).getDeckCards().size() + normalCardCounter > 20) {
            // dialogBox to say "can not add more than 20 minion and spell to deck"
            return false;
        }
        return true;

    }

}
