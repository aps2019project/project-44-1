package controller.fxmlControllers;

import client.RequestSender;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import server.Environment;
import server.Request;
import server.RequestType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class LoginPageController implements Initializable {
    public GridPane root;
    public Button logInButton;
    public Button signUpButton;
    public Button submitButton;
    public PasswordField passwordTextField;
    public TextField usernameTextField;
    public Label label;
    public Button leaderboard;
    private static final int DISAPPEARING_LABEL_DELAY = 1000;
    private static LeaderboardController controller = new LeaderboardController();

    /**
     * incredibly runs twice!!!
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logInButton.setOnAction(event -> submitButton.setText("LOG IN"));
        signUpButton.setOnAction(event -> submitButton.setText("SIGN UP"));
        submitButton.setOnAction(event -> action(submitButton.getText().equals("LOG IN")));
    }

    private void action(boolean isLogin) {
        if (usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty())
            return;
        Request request = new Request(Environment.LOGIN_PAGE);
        if (isLogin)
            request.setRequestType(RequestType.SIGN_IN);
        else
            request.setRequestType(RequestType.SIGN_UP);
        request.setUsername(usernameTextField.getText());
        request.setPassword(passwordTextField.getText());
        RequestSender.getInstance().sendRequest(request);
    }

    public void appearLabel(String text) {
        label.setText(text);
        label.setVisible(true);
        disappearLabel(label);
    }

    static void disappearLabel(Label l) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                l.setVisible(false);
            }
        };
        timer.schedule(task, DISAPPEARING_LABEL_DELAY);
    }

    public void showLeaderboard() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/cardInShop.fxml"));
        loader.setController(controller);
        try {
            label.getScene().setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LeaderboardController getController() {
        return controller;
    }

}
