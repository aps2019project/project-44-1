package view.fxmls.wrapperClasses;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import models.Card;
import models.Placeable;


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


    public CardContainer(int mana, int ap, int hp, String name) {
        manaLabel = new Label(Integer.toString(mana));
        manaLabel.setStyle("-fx-font-size: 24px;-fx-text-fill: black;-fx-text-alignment: center;-fx-text-overrun: ELLIPSIS;-fx-alignment: center;" +
                "-fx-pref-width: 51;-fx-pref-height: 39;-fx-font-weight: bold");
        manaLabel.setLayoutX(4.5);
        manaLabel.setLayoutY(17);

        attackPointLabel = new Label(Integer.toString(ap));
        attackPointLabel.setStyle("-fx-font-size: 27px;-fx-text-fill: #efff09;-fx-text-alignment: center;-fx-text-overrun: ELLIPSIS;-fx-alignment: center;" +
                "-fx-pref-width: 60;-fx-pref-height: 50;-fx-font-weight: bold");
        attackPointLabel.setLayoutX(49);
        attackPointLabel.setLayoutY(214);

        healthPointLabel = new Label(Integer.toString(hp));
        healthPointLabel.setStyle("-fx-font-size: 27px;-fx-text-fill: red;-fx-text-alignment: center;-fx-text-overrun: ELLIPSIS;-fx-alignment: center;" +
                "-fx-pref-width: 60;-fx-pref-height: 50;-fx-font-weight: bold");
        healthPointLabel.setLayoutX(200);
        healthPointLabel.setLayoutY(214);

        nameLabel = new Label(name);
        nameLabel.setStyle("-fx-font-size: 18px;-fx-text-fill: white;-fx-text-alignment: center;-fx-text-overrun: ELLIPSIS;-fx-alignment: center;" +
                "-fx-pref-width: 290;-fx-pref-height: 86;-fx-font-weight: bold");
        nameLabel.setLayoutX(6);
        nameLabel.setLayoutY(279);

         checkBox.setLayoutX(21);
         checkBox.setLayoutY(386 );
         checkBox.setPrefSize(10,10);

        checkBox.setLayoutX(21);
        checkBox.setLayoutY(390);
        checkBox.setStyle("-fx-font-size: 20");

        anchorPane.getChildren().addAll(manaLabel, attackPointLabel, healthPointLabel, nameLabel,checkBox);

    }

    public CardContainer(Card card) {
        this(card.getNeededMana(), card.getAP(), card.getHP(), card.getName());
        this.card = card;
    }

    public CardContainer(Placeable placeable) {
        this(placeable.getNeededMana(), 0, 0, placeable.getName());
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
}
