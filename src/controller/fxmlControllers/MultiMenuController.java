package controller.fxmlControllers;

import controller.logicController.AccountController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import models.Account;
import models.Game;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MultiMenuController implements Initializable {
    public ListView<String> accounts;
    private AccountController accountController = AccountController.getInstance();
    public Button back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(actionEvent -> Game.getInstance().loadPage(back,"/view/fxmls/battleMenu.fxml"));
        populateList();
    }

    private void populateList() {
        ArrayList<Account> arrayList = Game.getAccounts();
        ObservableList<String> strings = FXCollections.observableArrayList();
        Account account = accountController.getAccount();
        for (Account a : arrayList) {
            if (a.equals(account))
                continue;
            strings.add(a.getUsername());
        }
        accounts.setItems(strings);
        accounts.setOnMouseClicked(mouseEvent -> System.out.println(accounts.getSelectionModel().getSelectedItem()));
    }

}
