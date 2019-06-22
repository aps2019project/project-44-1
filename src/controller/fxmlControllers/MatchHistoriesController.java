package controller.fxmlControllers;

import controller.AccountController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Account;
import view.fxmls.wrapperClasses.History;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MatchHistoriesController implements Initializable {
    @FXML
    public TableView<History> table;
    public TableColumn<History, String> opponent;
    public TableColumn<History, String> w_l;
    public TableColumn<History, String> time;
    public Button back;
    private AccountController accountController = AccountController.getInstance();
    private Account account;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(event -> turnBack());
        account = accountController.getAccount();
        populateTable();
    }

    private void populateTable() {
        ObservableList<History> list = FXCollections.observableArrayList();
        list.addAll(History.toHistory(account.getHistories()));
        table.setItems(list);
        opponent.setCellValueFactory(new PropertyValueFactory<>("username"));
        w_l.setCellValueFactory(new PropertyValueFactory<>("wins"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
    }

    private void turnBack() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/mainMenu.fxml"));
        try {
            table.getScene().setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
