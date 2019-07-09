package controller.fxmlControllers;

import client.RequestSender;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import server.Environment;
import server.Request;
import server.RequestType;

import java.net.URL;
import java.util.ResourceBundle;

public class CardInShopController implements Initializable {

    public AnchorPane pane;
    public Label mana;
    public Label AP;
    public Label HP;
    public Label name;
    public ImageView imageView;
    private boolean buy;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Request request = new Request(Environment.SHOP);
        pane.setOnMouseClicked(mouseEvent -> {
            if (buy) {
                request.setRequestType(RequestType.BUY);
                request.setCardToBuy(name.getText());
            } else {
                request.setRequestType(RequestType.SELL);
                request.setCardToSell(name.getText());
                request.setPaneToSell(pane);
            }
            RequestSender.getInstance().sendRequest(request);
        });
    }

    void setBuy(boolean buy) {
        this.buy = buy;
    }

}
