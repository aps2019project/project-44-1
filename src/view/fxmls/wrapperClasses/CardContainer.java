package view.fxmls.wrapperClasses;

import client.RequestSender;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import models.*;
import server.Environment;
import server.Request;
import server.RequestType;

public class CardContainer {
    private AnchorPane anchorPane = new AnchorPane();
    private Label manaLabel;
    private Label attackPointLabel;
    private Label healthPointLabel;
    private Label nameLabel;
    private ImageView imageView;
    private CheckBox checkBox;
    private Placeable card;

    {
        anchorPane.setStyle("-fx-background-image: url('/view/images/neutral_unit@2x.png'),url('/view/images/icon_mana.png');" +
                " -fx-background-size: 95% 90%, 20% 17%;" +
                "     -fx-background-repeat: no-repeat;\n" +
                "     -fx-background-position: 100% 0%, 0% 0%; ");
        anchorPane.setPrefSize(297, 440);
        checkBox = new CheckBox();
    }

    private CardContainer(String name) {
        Request request = new Request(Environment.COLLECTION);
        request.setRequestType(RequestType.GET_CARD);
        request.setCardToBuy(name);
        RequestSender.getInstance().sendRequest(request);
    }

    public CardContainer(Card card) {
        this(card.getName());
        this.card = card;
    }

    public CardContainer(Placeable placeable) {
        this(placeable.getName());
        this.card = placeable;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public Placeable getCard() {
        return card;
    }

    public void createContainer(Placeable c) {
        manaLabel = new Label(Integer.toString(c.getNeededMana()));
        manaLabel.setStyle("-fx-font-size: 24px;-fx-text-fill: black;-fx-text-alignment: center;-fx-text-overrun: ELLIPSIS;-fx-alignment: center;" +
                "-fx-pref-width: 51;-fx-pref-height: 39;-fx-font-weight: bold");
        manaLabel.setLayoutX(4.5);
        manaLabel.setLayoutY(17);

        if (c instanceof Card)
            attackPointLabel = new Label(Integer.toString(((Card) c).getAP()));
        else
            attackPointLabel = new Label(Integer.toString(0));
        attackPointLabel.setStyle("-fx-font-size: 27px;-fx-text-fill: #efff09;-fx-text-alignment: center;-fx-text-overrun: ELLIPSIS;-fx-alignment: center;" +
                "-fx-pref-width: 60;-fx-pref-height: 50;-fx-font-weight: bold");
        attackPointLabel.setLayoutX(49);
        attackPointLabel.setLayoutY(214);

        if (c instanceof Card)
            healthPointLabel = new Label(Integer.toString(((Card) c).getHP()));
        else
            healthPointLabel = new Label(Integer.toString(0));
        healthPointLabel.setStyle("-fx-font-size: 27px;-fx-text-fill: red;-fx-text-alignment: center;-fx-text-overrun: ELLIPSIS;-fx-alignment: center;" +
                "-fx-pref-width: 60;-fx-pref-height: 50;-fx-font-weight: bold");
        healthPointLabel.setLayoutX(200);
        healthPointLabel.setLayoutY(214);

        nameLabel = new Label(c.getName() + "\n" + c.getClass().getSimpleName());
        nameLabel.setStyle("-fx-font-size: 18px;-fx-text-fill: white;-fx-text-alignment: center;-fx-text-overrun: ELLIPSIS;-fx-alignment: center;" +
                "-fx-pref-width: 290;-fx-pref-height: 86;-fx-font-weight: bold");
        nameLabel.setLayoutX(6);
        nameLabel.setLayoutY(279);

        checkBox.setLayoutX(21);
        checkBox.setLayoutY(386);
        checkBox.setPrefSize(10, 10);

        checkBox.setLayoutX(21);
        checkBox.setLayoutY(390);
        checkBox.setStyle("-fx-font-size: 20");
        if (!(c instanceof Item)) {
            imageView = new ImageView(new Image(c.getPath()));
            imageView.setFitHeight(191);
            imageView.setFitWidth(235);
            imageView.setX(40);
            imageView.setY(10);
            if (!(c instanceof Spell)) {
                imageView.setScaleX(1.5);
                imageView.setScaleY(1.5);
            }
            anchorPane.getChildren().addAll(manaLabel, attackPointLabel, healthPointLabel, nameLabel, checkBox, imageView);
        } else {
            anchorPane.getChildren().addAll(manaLabel, attackPointLabel, healthPointLabel, nameLabel, checkBox);
        }

    }

}
