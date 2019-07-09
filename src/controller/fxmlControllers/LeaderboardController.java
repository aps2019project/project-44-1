package controller.fxmlControllers;

import Main.Main;
import client.RequestSender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Account;
import server.Environment;
import server.Request;
import server.RequestType;
import view.fxmls.wrapperClasses.Leader;

import java.io.IOException;
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
        back.setOnAction(event -> loadLoginPage());
        showTable();
    }

    private void loadLoginPage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/loginPage.fxml"));
        try {
            Main.getStage().getScene().setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setAccounts(ArrayList<Account> accounts) {
        LeaderboardController.accounts = accounts;
    }

    public static void setOnlineAccounts(HashMap<String, Account> onlineAccounts) {
        LeaderboardController.onlineAccounts = onlineAccounts;
    }
}
