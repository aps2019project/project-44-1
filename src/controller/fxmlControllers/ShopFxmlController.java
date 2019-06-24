package controller.fxmlControllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import models.Card;
import models.Game;
import models.Placeable;
import models.Shop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShopFxmlController implements Initializable {

    public Button back;
    public FlowPane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back.setOnAction(actionEvent -> Game.getInstance().loadPage(back, "/view/fxmls/mainMenu.fxml"));
        Shop shop = Shop.getInstance();
        for (Placeable c : shop.getCards()) {
            if (!(c instanceof Card))
                continue;
            try {
                Node loader = FXMLLoader.load(getClass().getResource("/view/fxmls/cardInShop.fxml"));
                pane.getChildren().add(loader);
            } catch (IOException e) {
                e.printStackTrace();
            }
//        System.out.println(scrolane.getContent());
        }
    }

    private AnchorPane craftGraphics(Card card) {
        AnchorPane anchorPane = new AnchorPane();
        BackgroundImage myBI = new BackgroundImage(new Image(card.getUrl(), 32, 32,
                false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI, defaultBackground(), manaIcon()));
        addLabels(card, anchorPane);
        return anchorPane;
    }

    private void addLabels(Card card, AnchorPane anchorPane) {
        Label mana = new Label();
        Label AP = new Label();
        Label HP = new Label();
        Label desc = new Label();
        Label name = new Label();
        mana.setText(String.valueOf(card.getNeededMana()));
        AP.setText(String.valueOf(card.getAP()));
        HP.setText(String.valueOf(card.getHP()));
        desc.setText(String.valueOf(card.getDesc()));
        name.setText(String.valueOf(card.getName()));
        anchorPane.getChildren().addAll(mana, AP, HP, name, desc);
    }

    private BackgroundImage defaultBackground() {
        return new BackgroundImage(new Image("src/view/images/neutral_unit@2x.png",
                32, 32, false, false), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
    }

    private BackgroundImage manaIcon() {
        return new BackgroundImage(new Image("src/view/images/neutral_unit@2x.png",
                32, 32, false, false), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
    }

    private String getPath(Card card) {
        return null;
    }

}
