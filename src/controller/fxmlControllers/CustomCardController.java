package controller.fxmlControllers;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.Game;

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

    private void backEnd() {
        new Thread(() -> {

        }).start();
    }

    private String getCAError() {
        String s = "invalid";
        if (type.getSelectionModel().getSelectedIndex() == 0)
            s += "special power \ncooldown";
        else s += "special power \nactivation";
        return s + "!!!";
    }

}
