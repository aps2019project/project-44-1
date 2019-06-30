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
        type.getItems().addAll("HERO", "SPELL", "MINION");
        type.valueProperty().addListener((observableValue, s, t1) -> {
            if (t1.equals("HERO"))
                heroState(true);
            else if (t1.equals("MINION"))
                heroState(false);
            else
                spellState();
        });
    }

    private void heroState(boolean isHero) {
        AP.setVisible(true);
        HP.setVisible(true);
        attackType.setVisible(true);
        attackType.getItems().addAll("MELEE", "RANGED", "HYBRID");
        range.setVisible(true);
        cool_active.setVisible(true);
        if (isHero) {
            cool_active.setPromptText("special power activation");
        } else
            cool_active.setPromptText("special power cooldown");
    }

    private void spellState() {
    }

    private void checkFinish() {
        if (wrongInfo(name, "invalid name!!!", "^\\w+$"))
            return;
        if (wrongInfo(cost, "invalid cost!!!", "^\\d+$"))
            return;
        if (type.getSelectionModel().getSelectedItem().equals("")) {
            appearLabel("select card type!!!");
            return;
        }
        if (wrongInfo(AP, "invalid AP!!!", "^\\d+$"))
            return;
        if (wrongInfo(HP, "invalid HP!!!", "^\\d+$"))
            return;
        if (attackType.getSelectionModel().getSelectedItem().equals("")) {
            appearLabel("select attackType!!!");
            return;
        }
        appearLabel("card created successfully!!!");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Game.getInstance().loadPage(finish, "/view/fxmls/mainMenu.fxml");
    }

    private boolean wrongInfo(TextField tf, String error, String regex) {
        if (tf.getText().matches(regex)) {
            return true;
        }
        appearLabel(error);
        return false;
    }

    private void appearLabel(String text) {
        message.setText(text);
        message.setStyle("-fx-background-color: rgba(255, 212, 134, 0.48)");
        message.setVisible(true);
        LoginPageController.disappearLabel(message);
    }

}
