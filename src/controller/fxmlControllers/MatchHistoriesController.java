package controller.fxmlControllers;

import Main.Main;
import client.ResponseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public TableView<History> table;
    public TableColumn<History, String> opponent;
    public TableColumn<History, String> w_l;
    public TableColumn<History, String> time;
    public Button back;
    private static Account account;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(event -> loadMainMenu());
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

    public static void setAccount(Account account) {
        MatchHistoriesController.account = account;
    }

    public void loadMainMenu() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/mainMenu.fxml"));
        try {
            Main.getStage().getScene().setRoot(loader.load());
            ResponseHandler.getInstance().setMainMenuController(loader.getController());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
