package controller.fxmlControllers;

import client.RequestSender;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import models.Enums.BattleMode;
import server.Environment;
import server.Request;
import server.RequestType;

import java.net.URL;
import java.util.ResourceBundle;

import static models.Enums.BattleMode.*;

public class MultiMenuController implements Initializable {

    public Button back;
    public ComboBox<String> choice;
    public TextField number;
    public Label look;
    public Button cancel;
    private static final String error = "invalid flags number";
    private static final String flag_bound = "[1-9]";
    private static final int MULTI1 = 20;
    private static final int MULTI2 = 22;
    private static final String canc = "cancel";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(actionEvent -> MainMenuController.loadPage("/view/fxmls/battleMenu.fxml"));
        cancel.setOnAction(actionEvent -> action());
        craftComboBox();
        craftTextField();
    }

    private void craftTextField() {
        number.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (!t1) {
                if (!number.getText().matches(flag_bound)) {
                    number.setText(error);
                }
            }
        });
    }

    private void craftComboBox() {
        choice.getItems().addAll(DEATH_MATCH.getMessage(),
                BattleMode.CAPTURE_FLAG_1.getMessage(), BattleMode.CAPTURE_FLAG_2.getMessage());
        coloring();
        choice.valueProperty().addListener((observableValue, s, t1) -> {
            if (t1.equals(CAPTURE_FLAG_2.getMessage()))
                number.setDisable(false);
            else
                number.setDisable(true);
        });
    }

    private void coloring() {
        choice.setCellFactory(new Callback<>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item);
                            if (item.equals(DEATH_MATCH.getMessage())) {
                                setTextFill(Color.RED);
                            } else if (item.equals(CAPTURE_FLAG_1.getMessage())) {
                                setTextFill(Color.GREEN);
                            } else {
                                setTextFill(Color.BLUE);
                            }
                        } else
                            setText(null);
                    }
                };
            }
        });
    }

    private void action() {
        if (cancel.getText().equals(canc)) {
            regret();
        }
        if (choice.getSelectionModel().getSelectedItem().equals(""))
            return;
        if (choice.getSelectionModel().getSelectedIndex() == 2 && !number.getText().matches(flag_bound))
            return;
        search();
    }

    private void search() {
        look.setVisible(true);
        cancel.setText(canc);
        number.setDisable(true);
        choice.setDisable(true);
        back.setDisable(true);
        Request request = new Request(Environment.BATTLE);
        request.setRequestType(RequestType.MULTI_PLAYER);
        request.setState(getState());
        RequestSender.getInstance().sendRequest(request);
    }

    private void regret() {
        look.setVisible(false);
        cancel.setText("start");
        choice.setDisable(false);
        back.setDisable(false);
        Request request = new Request(Environment.BATTLE);
        request.setRequestType(RequestType.REGRETED);
        RequestSender.getInstance().sendRequest(request);
    }

    private int getState() {
        switch (choice.getSelectionModel().getSelectedIndex()) {
            case 0:
                return MULTI1;
            case 1:
                return MULTI2;
            default:
                return MULTI1 + Integer.parseInt(number.getText());
        }
    }

}
