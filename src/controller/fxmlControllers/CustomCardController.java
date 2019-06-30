package controller.fxmlControllers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.*;
import models.Enums.AttackType;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomCardController implements Initializable {
    public TextField name;
    public ComboBox<String> type;
    public TextField target;
    public TextField buffs;
    public TextField AP;
    public TextField HP;
    public ComboBox<String> attackType;
    public TextField range;
    public TextField specialPower;
    public TextField cool_active;
    public TextField cost;
    public Button finish;
    public Button back;
    public Label message;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(actionEvent -> Game.getInstance().loadPage(finish, "/view/fxmls/mainMenu.fxml"));
        craftChoiceBox();
        finish.setOnAction(actionEvent -> checkFinish());
    }

    private void craftChoiceBox() {
        type.getItems().addAll("HERO", "MINION", "SPELL");
        type.valueProperty().addListener((observableValue, s, t1) -> {
            if (t1.equals("HERO"))
                setState(true, false);
            else if (t1.equals("MINION"))
                setState(false, false);
            else
                setState(false, true);
        });
    }

    private void setState(boolean isHero, boolean isSpell) {
        AP.setVisible(!isSpell);
        HP.setVisible(!isSpell);
        attackType.setVisible(!isSpell);
        if (attackType.getItems().size() == 0)
            attackType.getItems().addAll("MELEE", "RANGED", "HYBRID");
        range.setVisible(!isSpell);
        cool_active.setVisible(!isSpell);
        if (isHero) {
            cool_active.setPromptText("special power activation");
        } else
            cool_active.setPromptText("special power cooldown");
    }

    private void checkFinish() {
        if (wrongInfo(name, "invalid name!!!", "^\\w+$"))
            return;
        if (wrongInfo(cost, "invalid cost!!!", "^\\d+$"))
            return;
        if (type.getSelectionModel().getSelectedItem() == null) {
            appearLabel("select card type!!!");
            return;
        }
        if (notSpellChecks())
            return;
        appearLabel("card created successfully!!!");
        backEnd();
        Game.getInstance().loadPage(finish, "/view/fxmls/mainMenu.fxml");
    }

    private boolean notSpellChecks() {
        if (type.getSelectionModel().getSelectedIndex() != 2) {
            if (wrongInfo(AP, "invalid AP!!!", "^\\d+$"))
                return true;
            if (wrongInfo(HP, "invalid HP!!!", "^\\d+$"))
                return true;
            if (attackType.getSelectionModel().getSelectedItem() == null) {
                appearLabel("select attackType!!!");
                return true;
            }
            if (wrongInfo(range, "invalid range!!!", "^\\d+$"))
                return true;
            return wrongInfo(cool_active, getCAError(), "^\\d+$");
        }
        return false;
    }

    private boolean wrongInfo(TextField tf, String error, String regex) {
        if (tf.getText().matches(regex)) {
            return false;
        }
        appearLabel(error);
        return true;
    }

    private void appearLabel(String text) {
        message.setText(text);
        message.setStyle("-fx-background-color: rgba(255, 212, 134, 0.48)");
        message.setVisible(true);
        LoginPageController.disappearLabel(message);
    }

    private String getCAError() {
        String s = "invalid";
        if (type.getSelectionModel().getSelectedIndex() == 0)
            s += "special power \ncooldown";
        else s += "special power \nactivation";
        return s + "!!!";
    }

    //-------------------------------------------------------------------------------
    private void backEnd() {
        new Thread(() -> {

            Gson gson = new Gson();
            JsonReader reader = null;
            switch (type.getSelectionModel().getSelectedIndex()) {
                case 0:
                    try {
                        reader = new JsonReader(new FileReader("src\\models\\customHeroes.json"));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    JsonArray array = gson.fromJson(reader, JsonArray.class);
                    Hero hero = getHero();
                    Hero[] heroes = gson.fromJson(array, Hero[].class);
                    write(gson, hero, heroes, "src\\models\\customHeroes.json");
                    break;
                case 1:
                    try {
                        reader = new JsonReader(new FileReader("src\\models\\customMinions.json"));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    array = gson.fromJson(reader, JsonArray.class);
                    Minion minion = getMinion();
                    Minion[] minions = gson.fromJson(array, Minion[].class);
                    write(gson, minion, minions, "src\\models\\customMinions.json");
                    break;
                case 2:
            }
        }).start();
    }

    private void write(Gson gson, Card card, Card[] cards, final String path) {
        Shop shop = Shop.getInstance();
        shop.getCards().add(card);
        try {
            FileWriter writer = new FileWriter(path);
            Card[] cards1;
            if (cards != null) {
                int length = cards.length;
                cards1 = getCards(card, length);
                System.arraycopy(cards, 0, cards1, 0, length);
                cards1[length - 1] = card;
            } else {
                cards1 = getCards(card, 0);
                cards1[0] = card;
            }
            writer.write(gson.toJson(cards1));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Card[] getCards(Card card, int length) {
        Card[] cards1;
        if (card instanceof Hero)
            cards1 = new Hero[length + 1];
        else
            cards1 = new Minion[length + 1];
        return cards1;
    }

    private Hero getHero() {
        Hero hero = new Hero();
        fielder(hero);
        hero.setSpecialPowerCoolDown(Integer.parseInt(cool_active.getText()));
        return hero;
    }

    private Minion getMinion() {
        Minion minion = new Minion();
        fielder(minion);
        minion.setSpecialPowerActivation(cool_active.getText());
        return minion;
    }

    private void fielder(Card card) {
        card.setName(name.getText());
        card.setRange(Integer.parseInt(range.getText()));
        card.setCost(Integer.parseInt(cost.getText()));
        card.setAP(Integer.parseInt(AP.getText()));
        card.setHP(Integer.parseInt(HP.getText()));
        card.setAttackType(AttackType.valueOf(attackType.getSelectionModel().getSelectedItem()));
        card.setSpecialPower(specialPower.getText());
    }

}
