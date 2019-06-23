package controller.fxmlControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CardInShopController implements Initializable {

    @FXML
    public AnchorPane pane;
    public Label mana;
    public Label AP;
    public Label HP;
    public Label desc;
    public Label name;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        BackgroundImage myBI = new BackgroundImage(new Image("my url", 32, 32, false, true,true),
//                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
//                BackgroundSize.DEFAULT);
//        pane.setBackground(new Background(myBI));
    }
}

