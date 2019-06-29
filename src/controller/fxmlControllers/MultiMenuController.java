package controller.fxmlControllers;

import controller.logicController.AccountController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import models.Account;
import models.Enums.BattleMode;
import models.Game;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static models.Enums.BattleMode.*;

public class MultiMenuController implements Initializable {
    public ListView<String> accounts;
    private AccountController accountController = AccountController.getInstance();
    public Button back;
    public ComboBox<String> choice;
    public TextField number;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(actionEvent -> Game.getInstance().loadPage(back, "/view/fxmls/battleMenu.fxml"));
        populateList();
        craftComboBox();
        craftTextField();
    }

    private void craftTextField() {
        number.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (!t1) {
                if (!number.getText().matches("[1-5]")) {
                    number.setText("invalid flags number");
                }

            }
        });
    }

    private void craftComboBox() {
        choice.getItems().addAll(DEATH_MATCH.getMessage(),
                BattleMode.CAPTURE_FLAG_1.getMessage(), BattleMode.CAPTURE_FLAG_2.getMessage());
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
        choice.valueProperty().addListener((observableValue, s, t1) -> {
            if (t1.equals(CAPTURE_FLAG_2.getMessage()))
                number.setDisable(false);
            else
                number.setDisable(true);
        });
    }

    private void populateList() {
        ArrayList<Account> arrayList = Game.getAccounts();
        ObservableList<String> strings = FXCollections.observableArrayList();
        Account account = accountController.getAccount();
        for (Account a : arrayList) {
            if (a.equals(account) || !a.isReadyToPlay())
                continue;
            strings.add(a.getUsername());
        }
        accounts.setItems(strings);
        accounts.setOnMouseClicked(mouseEvent -> checkRequirement());
    }

    private void checkRequirement() {
        switch (choice.getSelectionModel().getSelectedIndex()) {
            case 0:
                enterBattle(AccountController.MULTI1, accounts.getSelectionModel().getSelectedItem());
                break;
            case 1:
                enterBattle(AccountController.MULTI2, accounts.getSelectionModel().getSelectedItem());
                break;
            case 2:
                enterBattle(AccountController.MULTI1 + Integer.parseInt(number.getText()),
                        accounts.getSelectionModel().getSelectedItem());
        }
    }

    void enterBattle(int state, String... userName) {
        MapController controller = new MapController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/map.fxml"));
        loader.setController(controller);
        startThread(state, userName);
        try {
            back.getScene().setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startThread(int state, String... userName) {
        accountController.setState(state);
        if (userName != null) {
            accountController.setOpponent(Game.getAccount(userName[0]));
        }
        accountController.setDaemon(true);
        accountController.start();
    }

}
