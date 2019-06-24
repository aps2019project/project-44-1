package controller.fxmlControllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import models.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ShopFxmlController implements Initializable {

    public Button back;
    public FlowPane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back.setOnAction(actionEvent -> Game.getInstance().loadPage(back, "/view/fxmls/mainMenu.fxml"));
        craftGraphics();
    }

    private void craftGraphics() {
        Shop shop = Shop.getInstance();
        for (Placeable c : shop.getCards()) {
            if (!(c instanceof Card))
                continue;
            try {
                AnchorPane loader = FXMLLoader.load(getClass().getResource("/view/fxmls/cardInShop.fxml"));
                specifyImageAndText((Card) c, loader);
                pane.getChildren().add(loader);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void specifyImageAndText(Card c, AnchorPane loader) {
        for (Node n : loader.getChildren()) {
            if (n instanceof ImageView)
                getImageView(((ImageView) n), c.getUrl());
            if (n instanceof Label) {
                getLabelText(c, (Label) n);
            }
        }
    }

    private void getImageView(ImageView view, String url) {
        Image image = new Image("view/images/cardGifs/boss_decepticleprime_breathing.gif");
        view.setImage(image);
        view.setScaleX(2);
        view.setScaleY(2);
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
                break;
            case "desc":
                label.setText(c.getDesc());
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
        return s;
    }

}
