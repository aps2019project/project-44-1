package controller.fxmlControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class LeaderboardController {
    @FXML
    public ListView<String> leaderboard;
    private ObservableList list = FXCollections.observableArrayList();

}
