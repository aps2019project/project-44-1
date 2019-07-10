package controller.fxmlControllers;

import Main.Main;
import client.CardBuilder;
import client.RequestSender;
import client.ResponseHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import models.*;
import server.Environment;
import server.Request;
import server.RequestType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShopFxmlController implements Initializable {

    public Button back;
    public FlowPane pane;       //shop flowPane
    public Label money;
    public FlowPane collectionPane;
    public Label message;
    public ScrollPane shop;
    public TextField search;
    private static Account account;
    private CardBuilder builder = new CardBuilder();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back.setOnAction(actionEvent -> backAction());
        money.setText(Integer.toString(account.getMoney()));
        for (Placeable c : builder.getCards()) {
            fillPanes(c, pane, true);
        }
        for (Placeable c : account.getCollection().getCollectionCards()) {
            fillPanes(builder.getCard(c.getName()), collectionPane, false);
        }

        search.setOnKeyPressed(actionEvent -> {
            if (actionEvent.getCode() == KeyCode.ENTER)
                searchInShop();
        });
    }

    private void searchInShop() {
        Request request = new Request(Environment.SHOP);
        request.setRequestType(RequestType.SEARCH_IN_SHOP);
        request.setSearchedString(search.getText());
        RequestSender.getInstance().sendRequest(request);
    }

    public void fillPanes(String name, FlowPane flowPane, boolean buy) {
        this.fillPanes(builder.getCard(name), flowPane, buy);
    }

    private void fillPanes(Placeable c, FlowPane flowPane, boolean buy) {
        if (!(c instanceof Card))
            return;
        try {
            CardInShopController inShopController = new CardInShopController();
            inShopController.setBuy(buy);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/cardInShop.fxml"));
            loader.setController(inShopController);
            AnchorPane anchorPane = loader.load();
            specifyImageAndText((Card) c, anchorPane, c instanceof Spell);
            flowPane.getChildren().add(anchorPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void specifyImageAndText(Card c, AnchorPane loader, boolean isSpell) {
        for (Node n : loader.getChildren()) {
            if (n instanceof ImageView)
                getImageView(((ImageView) n), c.getPath(), isSpell);
            if (n instanceof Label) {
                getLabelText(c, (Label) n);
            }
        }
    }

    private void getImageView(ImageView view, String url, boolean isSpell) {
        Image image;
        if (url == null) {
            image = getImage();
        } else
            image = new Image(url);
        if (isSpell) {
            view.setFitHeight(70);
            view.setFitWidth(70);
            view.setX(60);
            view.setY(70);
        }
        view.setImage(image);
        view.setScaleX(1.5);
        view.setScaleY(1.5);
    }

    private Image getImage() {
        return new Image("view/images/cardGifs/boss_grym_breathing.gif");
    }

    private void getLabelText(Card c, Label label) {
        switch (label.getId()) {
            case "mana":
                label.setText(String.valueOf(c.getNeededMana()));
                break;
            case "HP":
                label.setText(String.valueOf(c.getHP()));
                break;
            case "AP":
                label.setText(String.valueOf(c.getAP()));
                break;
            case "name":
                label.setText(getName(c));
        }
    }

    private String getName(Card c) {
        String s;
        s = c.getName();
        s += "\n";
        if (c instanceof Minion) {
            s += "MINION";
        } else if (c instanceof Spell) {
            s += "SPELL";
        } else if (c instanceof Hero) {
            s += "HERO";
        }
        s += ("\n" + c.getCost());
        return s;
    }

    public static Account getAccount() {
        return account;
    }

    public static void setAccount(Account account) {
        ShopFxmlController.account = account;
    }

    public static void backAction() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/fxmls/mainMenu.fxml"));
        try {
            Main.getStage().getScene().setRoot(loader.load());
            ResponseHandler.getInstance().setMainMenuController(loader.getController());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
