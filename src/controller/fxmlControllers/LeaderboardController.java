package controller.fxmlControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Account;
import view.fxmls.wrapperClasses.Leader;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class LeaderboardController implements Initializable {
    public TableView<Leader> table;
    public TableColumn<Leader, String> username;
    public TableColumn<Leader, Integer> wins;
    public TableColumn<Leader, String> online;
    public Button back;
    private static ArrayList<Account> accounts;
    private static HashMap<String, Account> onlineAccounts;

    public void showTable() {
        ObservableList<Leader> list = FXCollections.observableArrayList();
        list.addAll(Leader.accountToLeader(accounts, onlineAccounts));
        table.setItems(list);
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        wins.setCellValueFactory(new PropertyValueFactory<>("wins"));
        online.setCellValueFactory(new PropertyValueFactory<>("online"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back.setOnAction(event -> MainMenuController.loadPage("/view/fxmls/loginPage.fxml"));
        showTable();
    }

    public static void setAccounts(ArrayList<Account> accounts) {
        LeaderboardController.accounts = accounts;
    }

    public static void setOnlineAccounts(HashMap<String, Account> onlineAccounts) {
        LeaderboardController.onlineAccounts = onlineAccounts;
    }

}
