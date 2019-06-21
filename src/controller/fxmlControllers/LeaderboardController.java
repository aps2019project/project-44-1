package controller.fxmlControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Game;
import view.fxmls.wrapperClasses.Leader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LeaderboardController implements Initializable {
    @FXML
    public TableView<Leader> table;
    public TableColumn<Leader, String> username;
    public TableColumn<Leader, Integer> wins;
    public Button back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(event -> turnBack());
        populateTable();
    }

    private void populateTable() {
        ObservableList<Leader> list = FXCollections.observableArrayList();
        list.addAll(Leader.accountToLeader(Game.getInstance().getSortedAccounts()));
        table.setItems(list);
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        wins.setCellValueFactory(new PropertyValueFactory<>("wins"));
    }

    private void turnBack() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/loginPage.fxml"));
        try {
            table.getScene().setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
