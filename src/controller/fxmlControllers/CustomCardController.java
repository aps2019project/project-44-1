package controller.fxmlControllers;

import client.CardBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.*;
import models.Enums.AttackType;
import models.Enums.SpecialPowerActivation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomCardController implements Initializable {
    private final String minionPath = "src\\models\\customMinions.json";
    private final String heroPath = "src\\models\\customHeroes.json";
    public TextField name;
    public ComboBox<String> type;
    public TextField AP;
    public TextField HP;
    public ComboBox<String> attackType;
    public TextField range;
    public TextField cool_active;
    public TextField cost;
    public Button finish;
    public Button back;
    public Label message;
    public ComboBox<String> specialPower;
    private CardBuilder cardBuilder = new CardBuilder();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(actionEvent -> Game.getInstance().loadPage(finish, "/view/fxmls/mainMenu.fxml"));
        craftChoiceBox();
        finish.setOnAction(actionEvent -> checkFinish());
    }

    private void craftChoiceBox() {
        type.getItems().addAll("HERO", "MINION");
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
        specialPower.setVisible(!isSpell);
        if (specialPower.getItems().size() == 0) {
            for (Placeable p : cardBuilder.getCards()) {
                if (p instanceof Spell)
                    specialPower.getItems().add(p.getName());
            }
        }
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
            if (specialPower.getSelectionModel().getSelectedItem() == null) {
                appearLabel("select specialPower!!!");
                return true;
            }
            if (type.getSelectionModel().getSelectedIndex() == 0)
                return wrongInfo(cool_active, getCAError(), "^\\d+$");
            else if (type.getSelectionModel().getSelectedIndex() == 1) {
                try {
                    SpecialPowerActivation.valueOf(cool_active.getText());
                } catch (IllegalArgumentException e) {
                    appearLabel("invalid \nSpecialPowerActivation!!!");
                }
            }
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

    /**
     * here a daemon thread writes the info into a json file, and add the card to the shop
     */
    //-------------------------------------------------------------------------------
    private void backEnd() {
        new Thread(() -> {
            Gson gson = new Gson();
            JsonReader reader = null;   //we sure that this order never changes
            switch (type.getSelectionModel().getSelectedIndex()) {
                case 0:     //hero
                    JsonArray array = getJsonElements(gson, reader, heroPath);
                    Hero hero = getHero();
                    Hero[] heroes = gson.fromJson(array, Hero[].class);
                    cardBuilder.addCard(hero);
                    Hero[] heroes1;
                    int length = heroes.length;
                    if (length != 0) {
                        heroes1 = new Hero[length + 1];
                        System.arraycopy(heroes, 0, heroes1, 0, length);
                        heroes1[length - 1] = hero;
                    } else {
                        heroes1 = new Hero[1];
                        heroes1[0] = hero;
                    }
                    write(gson, heroes1, heroPath);
                    break;
                case 1:     //minion
                    JsonArray array1 = getJsonElements(gson, reader, minionPath);
                    Minion minion = getMinion();
                    Minion[] minions = gson.fromJson(array1, Minion[].class);
                    cardBuilder.addCard(minion);
                    Minion[] minions1;
                    length = minions.length;
                    if (length != 0) {
                        minions1 = new Minion[length + 1];
                        System.arraycopy(minions, 0, minions1, 0, length);
                        minions1[length - 1] = minion;
                    } else {
                        minions1 = new Minion[1];
                        minions1[0] = minion;
                    }
                    write(gson, minions1, minionPath);
                    break;
                case 2:     //spell
            }
        }).start();
    }

    private JsonArray getJsonElements(Gson gson, JsonReader reader, String heroPath) {
        try {
            reader = new JsonReader(new FileReader(heroPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return gson.fromJson(reader, JsonArray.class);
    }

    private void write(Gson gson, Card[] cards, String path) {
        FileWriter writer;
        try {
            writer = new FileWriter(path);
            writer.write(gson.toJson(cards));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        try {
            minion.setSpecialPowerActivation(SpecialPowerActivation.valueOf(cool_active.getText()));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return minion;
    }

    private void fielder(Card card) {
        card.setName(name.getText());
        card.setRange(Integer.parseInt(range.getText()));
        card.setCost(Integer.parseInt(cost.getText()));
        card.setAP(Integer.parseInt(AP.getText()));
        card.setHP(Integer.parseInt(HP.getText()));
        card.setAttackType(AttackType.valueOf(attackType.getSelectionModel().getSelectedItem()));
        card.setSpecialPower(specialPower.getSelectionModel().getSelectedItem());
        card.setPath("view/images/cardGifs/boss_grym_breathing.gif");
    }

}
