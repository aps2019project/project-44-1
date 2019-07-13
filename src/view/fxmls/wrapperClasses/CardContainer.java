package view.fxmls.wrapperClasses;

import client.CardBuilder;
import client.RequestSender;
import controller.fxmlControllers.ShopFxmlController;
import javafx.animation.AnimationTimer;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import models.*;
import server.Environment;
import server.Request;
import server.RequestType;

import java.util.Optional;

public class CardContainer {
    private AnchorPane anchorPane = new AnchorPane();
    private Label manaLabel;
    private Label attackPointLabel;
    private Label healthPointLabel;
    private Label nameLabel;
    private Label buyer;
    private Label cost;
    private ImageView imageView;
    private CheckBox checkBox;
    private Placeable card;
    private static CardBuilder builder = new CardBuilder();
    private int id;
    private static ShopFxmlController shopFxmlController;
    private int remainedTime;

    {
        anchorPane.setStyle("-fx-background-image: url('/view/images/neutral_unit@2x.png'),url('/view/images/icon_mana.png');" +
                " -fx-background-size: 95% 90%, 20% 17%;" +
                "     -fx-background-repeat: no-repeat;\n" +
                "     -fx-background-position: 100% 0%, 0% 0%; ");
        anchorPane.setPrefSize(297, 440);
        checkBox = new CheckBox();
    }

//    private CardContainer(String name) {
//        Request request = new Request(Environment.COLLECTION);
//        request.setRequestType(RequestType.GET_CARD);
//        request.setCardToBuy(name);
//        RequestSender.getInstance().sendRequest(request);
//    }

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

    public CardContainer(String name) {
        Placeable c = builder.getCard(name);
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

    public CardContainer(Placeable placeable, int id, int remainedTime) {
        this(placeable);
        this.id = id;
        setForAuction(placeable, remainedTime);
    }

    public CardContainer(Card card, int id, int remainedTime) {
        this(card);
        this.id = id;
        setForAuction(card, remainedTime);
    }


    private void setForAuction(Placeable placeable, int remainedTime) {
        this.remainedTime = remainedTime;
        anchorPane.getChildren().remove(checkBox);
        anchorPane.setOnMouseClicked(event -> showDialogBox());
        buyer = new Label("Buyer : ");
        buyer.setStyle("-fx-text-fill: black;-fx-font-size: 12px");
        buyer.setPrefSize(267, 29);
        buyer.setLayoutX(21);
        buyer.setLayoutY(385);
        cost = new Label("Cost : " + placeable.getCost());
        cost.setStyle("-fx-text-fill: black;-fx-font-size: 12px");
        cost.setPrefSize(267, 29);
        cost.setLayoutX(21);
        cost.setLayoutY(411);
        Label timerLabel = new Label("0" + remainedTime / 60 + ":" + remainedTime % 60);
        timerLabel.relocate(20, 300);
        timerLabel.setStyle("-fx-text-fill: red");
        anchorPane.getChildren().addAll(buyer, cost, timerLabel);
        AnimationTimer animationTimer = new AnimationTimer() {
            private long lastTime = 0;
            private double time = 0;
            private long second = 1000000000;

            @Override
            public void handle(long now) {
                if (lastTime == 0) {
                    lastTime = now;
                }
                if (now > lastTime + second / 10) {
                    lastTime = now;
                    time += 1;
                    timerLabel.setText("0" + (int) (remainedTime - time / 10) / 60 + ":" + (int) (remainedTime - time / 10) % 60);
                }
                if (remainedTime - time / 10 < 0) {
                    shopFxmlController.selfDistructCardContainer(id);
                }
            }
        };
        animationTimer.start();

    }

    private void showDialogBox() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buy card");
        dialog.setContentText("Please enter your Proposed price:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(this::sendProposedCostToServer);
    }

    private void sendProposedCostToServer(String cost) {
        try {
            int price = Integer.parseInt(cost);
            Request request = new Request(Environment.SHOP);
            request.setRequestType(RequestType.SUGGEST_NEW_COST);
            request.setSuggestedPrice(price);
            request.setAuctionCardId(this.id);
            RequestSender.getInstance().sendRequest(request);
        } catch (Exception ignored) {

        }
    }

    public void updateAuctionCard(int newPrice, String newBuyer) {
        buyer.setText("Buyer : " + newBuyer);
        cost.setText("Cost : " + newPrice);
    }

    public int getId() {
        return id;
    }

    public static void setShopFxmlController(ShopFxmlController shopFxmlController) {
        CardContainer.shopFxmlController = shopFxmlController;
    }
}
