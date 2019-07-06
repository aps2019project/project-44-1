package controller.fxmlControllers;

import controller.logicController.ShopController;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShopFxmlController implements Initializable {

    public Button back;
    public FlowPane pane;       //shop flowPane
    public Label money;
    public FlowPane collectionPane;
    public Label message;
    public ScrollPane shop;
    public TextField search;
    private ShopController shopController = ShopController.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            money.setText(String.valueOf(shopController.getShop().getAccount().getMoney()));
        } catch (NullPointerException e) {

        }
        shopController.setShopFxmlController(this);
        back.setOnAction(actionEvent -> Game.getInstance().loadPage(back, "/view/fxmls/mainMenu.fxml"));
        craftGraphics();
        search.setOnKeyPressed(actionEvent -> {
            if (actionEvent.getCode() == KeyCode.ENTER)
                searchInShop();
        });
    }

    private void searchInShop() {
        Placeable p = shopController.getShop().getCard(search.getText());
        if (p != null) {
            ArrayList<Placeable> cards = shopController.getShop().getCards();
            double x = (double) (cards.indexOf(p) + 1) / cards.size();
            shop.setVvalue(x);
        }
    }

    private void craftGraphics() {
        Shop shop = Shop.getInstance();
        for (Placeable c : shop.getCards()) {
            fillPanes(c, pane, true);
        }
        Account account = shop.getAccount();
        if (account == null) {
            return;
        }
        Collection collection = account.getCollection();
        for (Placeable p : collection.getCollectionCards()) {
            System.out.println(p instanceof Card);
        }
        for (Placeable c : collection.getCollectionCards()) {
            fillPanes(c, collectionPane, false);
        }
    }

    public void fillPanes(Placeable c, FlowPane flowPane, boolean buy) {
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

}
