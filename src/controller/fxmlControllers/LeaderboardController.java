package controller.fxmlControllers;

import client.RequestSender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Account;
import server.Environment;
import server.Request;
import server.RequestType;
import view.fxmls.wrapperClasses.Leader;

import java.util.ArrayList;
import java.util.HashMap;

public class LeaderboardController {
    public TableView<Leader> table;
    public TableColumn<Leader, String> username;
    public TableColumn<Leader, Integer> wins;
    public TableColumn<Leader, String> online;
    public Button back;

    public void sendRequest() {
        Request request = new Request(Environment.LEADER_BOARD);
        request.setRequestType(RequestType.SHOW_LEADER_BOARD);
        RequestSender.getInstance().sendRequest(request);
    }

    public void showTable(ArrayList<Account> accounts, HashMap<String, Account> onlineAccounts) {
        ObservableList<Leader> list = FXCollections.observableArrayList();
        list.addAll(Leader.accountToLeader(accounts, onlineAccounts));
        table.setItems(list);
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        wins.setCellValueFactory(new PropertyValueFactory<>("wins"));
        online.setCellValueFactory(new PropertyValueFactory<>("online"));
    }

}
