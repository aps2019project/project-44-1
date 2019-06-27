package controller.fxmlControllers;

import controller.ShopController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CardInShopController implements Initializable {

    @FXML
    public AnchorPane pane;
    public Label mana;
    public Label AP;
    public Label HP;
    public Label name;
    public ImageView imageView;
    private boolean buy;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ShopController shopController = ShopController.getInstance();
        pane.setOnMouseClicked(mouseEvent -> {
            if (buy)
                shopController.buyCard(name.getText());
            else
                shopController.sellCard(name.getText(), pane);
        });
    }

    void setBuy(boolean buy) {
        this.buy = buy;
    }

}
