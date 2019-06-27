package controller.fxmlControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import models.Account;
import models.Game;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BattleMenuController implements Initializable {
    public ListView<String> accounts;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Account> arrayList = Game.getAccounts();
        ObservableList<String> strings = FXCollections.observableArrayList();
        for (Account a : arrayList) {
            strings.add(a.getUsername());
        }
        accounts.setItems(strings);
        accounts.setOnMouseClicked(mouseEvent -> System.out.println(accounts.getSelectionModel().getSelectedItem()));
    }

}
