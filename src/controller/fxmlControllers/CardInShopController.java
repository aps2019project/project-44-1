package controller.fxmlControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
    public ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

