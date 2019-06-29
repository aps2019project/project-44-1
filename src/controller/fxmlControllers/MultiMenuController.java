package controller.fxmlControllers;

import controller.logicController.AccountController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import models.Account;
import models.Game;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MultiMenuController implements Initializable {
    public ListView<String> accounts;
    private AccountController accountController = AccountController.getInstance();
    public Button back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(actionEvent -> Game.getInstance().loadPage(back, "/view/fxmls/battleMenu.fxml"));
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

    void enterBattle(int state, String... userName) {
        MapController controller = new MapController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/map.fxml"));
        loader.setController(controller);
        startThread(state, userName[0]);
        try {
            back.getScene().setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startThread(int state, String userName) {
        accountController.setState(state);
        accountController.setOpponent(Game.getAccount(userName));
        accountController.setDaemon(true);
        accountController.start();
    }

}
