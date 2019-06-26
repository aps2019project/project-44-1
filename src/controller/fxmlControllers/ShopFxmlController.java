package controller.fxmlControllers;

import controller.ShopController;
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
    public Label money;
    public Label message;
    private ShopController shopController = ShopController.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shopController.setShopFxmlController(this);
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
                getImageView(((ImageView) n), c.getPath());
            if (n instanceof Label) {
                getLabelText(c, (Label) n);
            }
        }
    }

    private void getImageView(ImageView view, String url) {
        Image image;
        if (url == null) {
            image = getImage();
        } else
            image = new Image(url);
        view.setImage(image);
        view.setScaleX(2);
        view.setScaleY(2);
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
