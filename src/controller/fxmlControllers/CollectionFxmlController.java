package controller.fxmlControllers;

import Main.Main;
import client.RequestSender;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import models.*;
import server.Environment;
import server.Request;
import server.RequestType;
import view.fxmls.wrapperClasses.CardContainer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CollectionFxmlController implements Initializable {
    public Button createDeckButton;
    public Button setMainDeckButton;
    public Button removeFromDeckButton;
    public Button deleteDeckButton;
    public FlowPane deckCardsFlowPane;
    public FlowPane collectionCardsFlowPane;
    public Button backButton;
    public Button addCardToDeckButton;
    public ComboBox<String> decks;
    private static Collection collection;
    public ScrollPane deckCardsScrollPane;
    public TextField searchBox;
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
        createDeckBtnOnAction();
        craftSearchBox();
    }

    private void craftSearchBox() {
        searchBox.setOnAction(event -> {
            if (!searchBox.getText().isEmpty()) {
                collectionCardsFlowPane.getChildren().clear();
                String choice = searchBox.getText();
                for (CardContainer card : collectionCards) {
                    if (card.getCard().getName().toLowerCase().startsWith(choice.toLowerCase()))
                        collectionCardsFlowPane.getChildren().add(card.getAnchorPane());
                }
            } else {
                collectionCardsFlowPane.getChildren().clear();
                for (CardContainer card : collectionCards) {
                    collectionCardsFlowPane.getChildren().add(card.getAnchorPane());
                }
            }

        });
    }

    private void createDeckBtnOnAction() {
        createDeckButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Create Deck");
            dialog.setHeaderText(null);
            dialog.setContentText("Please enter deck name: ");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent() && !result.get().isEmpty()) {
                Request request = new Request(Environment.COLLECTION);
                request.setRequestType(RequestType.CREATE_DECK);
                request.setDeckToAdd(result.get());
                RequestSender.getInstance().sendRequest(request);
                //todo
                try {
                    collection.createDeck(result.get());
                    decks.getItems().add(result.get());
                } catch (Exceptions.DuplicateNameForDeck duplicateNameForDeck) {
                    makeAlert("Error while making deck", "This name was used before!");
                }
                //todo
            }
        });
    }

    private void addCardToDeckBtn() {
        addCardToDeckButton.setOnAction(event -> {
            Request request = new Request(Environment.COLLECTION);
            request.setRequestType(RequestType.ADD_CARD_TO_DECK);
            request.setDeckToAddCardTo(decks.getValue());

            ArrayList<String> selectedCards = new ArrayList<>();

            if (decks.getValue() == null)
                return;

            for (CardContainer cardContainer : collectionCards) {
                if (cardContainer.getCheckBox().isSelected()) {
                    selectedCards.add(cardContainer.getCard().getName());
                    //todo
                    setCardContainer(cardContainer.getCard(), deckCardsFlowPane, deckCards);
                    collection.getDeck(decks.getValue()).addToDeck(cardContainer.getCard());
                    //todo
                }
            }
            request.setCardsToAddToDeck(selectedCards);
            RequestSender.getInstance().sendRequest(request);
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
            setCardContainer(card, collectionCardsFlowPane, collectionCards);
        }
    }

    private void setCardContainer(Placeable card, FlowPane cardsFlowPane, ArrayList<CardContainer> cards) {
        CardContainer cardContainer;
        if (card == null)
            return;
        if (card instanceof Card)
            cardContainer = new CardContainer((Card) card);
        else
            cardContainer = new CardContainer(card);
        cardsFlowPane.getChildren().add(cardContainer.getAnchorPane());
        cards.add(cardContainer);
    }

    private void addCreatedDecksToComboBox() {
        for (Deck deck : collection.getSortedDecks()) {
            decks.getItems().add(deck.getName());
        }
    }

    private void showSelectedDeckCards() {
        EventHandler<ActionEvent> eventEventHandler = event -> {
            deckCardsFlowPane.getChildren().clear();
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



    private void makeAlert(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

}
