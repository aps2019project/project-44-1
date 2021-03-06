package controller.fxmlControllers;

import Main.Main;
import client.RequestSender;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import server.Environment;
import server.Request;
import server.RequestType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    public Button battleButton;
    public Button collectionButton;
    public Button shopButton;
    public Button saveButton;
    public Button logoutButton;
    public Button exitButton;
    public Button historyButton;
    public Label error;
    public Button customCard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shopButton.setOnAction(event -> goToShop());
        collectionButton.setOnAction(event -> goToCollection());
        exitButton.setOnAction(actionEvent -> exit());
        logoutButton.setOnAction(actionEvent -> logout());
        saveButton.setOnAction(actionEvent -> save());
        battleButton.setOnAction(actionEvent -> goToBattle());
        historyButton.setOnAction(actionEvent -> showMatchHistories());
        customCard.setOnAction(actionEvent -> createCustomCard());
    }

    private void goToCollection() {
        Request request = new Request(Environment.MAIN_MENU);
        request.setRequestType(RequestType.ENTER_COLLECTION);
        RequestSender.getInstance().sendRequest(request);
    }

    private void goToShop() {
        Request request = new Request(Environment.MAIN_MENU);
        request.setRequestType(RequestType.ENTER_SHOP);
        RequestSender.getInstance().sendRequest(request);
    }

    private void save() {
        Request request = new Request(Environment.MAIN_MENU);
        request.setRequestType(RequestType.SAVE);
        RequestSender.getInstance().sendRequest(request);
    }

    private void exit() {
        Main.closeConnection();
    }

    private void goToBattle() {
        Request request = new Request(Environment.MAIN_MENU);
        request.setRequestType(RequestType.ENTER_BATTLE);
        RequestSender.getInstance().sendRequest(request);
    }

    private void logout() {
        Request request = new Request(Environment.MAIN_MENU);
        request.setRequestType(RequestType.LOG_OUT);
        RequestSender.getInstance().sendRequest(request);
    }

    private void showMatchHistories() {
        Request request = new Request(Environment.MAIN_MENU);
        request.setRequestType(RequestType.SHOW_MATCH_HISTORY);
        RequestSender.getInstance().sendRequest(request);
    }

    public void appearLabel(String text) {
        error.setText(text);
        error.setStyle("-fx-background-color: rgba(255, 212, 134, 0.48)");
        error.setVisible(true);
        LoginPageController.disappearLabel(error);
    }

    private void createCustomCard() {
        loadPage("/view/fxmls/customCard.fxml");
    }

    public static void loadPage(String url) {
        FXMLLoader loader = new FXMLLoader(MainMenuController.class.getResource(url));
        try {
            Main.getStage().getScene().setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
