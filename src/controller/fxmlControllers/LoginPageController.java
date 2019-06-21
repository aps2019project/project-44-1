package controller.fxmlControllers;

//import Main.Main;

import controller.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

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
    private final GameController gameController = GameController.getInstance();
    private static final int DISAPPEARING_LABEL_DELAY = 1000;

    /**
     * incredibly runs twice!!!
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logInButton.setOnAction(event -> submitButton.setText("LOG IN"));
        signUpButton.setOnAction(event -> submitButton.setText("SIGN UP"));
        handleSubmit();
    }

    public void handleSubmit() {
        submitButton.setOnAction(event -> {
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();
            if (submitButton.getText().equals("LOG IN")) {
                login(username, password);
            } else {
                signUp(username, password);
            }
        });
    }

    private void signUp(String username, String password) {
        gameController.createAccount(username,password);
        String labelText = gameController.getLabelText();
        if (labelText != null) {
            appearLabel(labelText);
        }
    }

    private void login(String username, String password) {
        gameController.login(username,password);
        String labelText = gameController.getLabelText();
        if (labelText != null) {
            appearLabel(labelText);
        }
    }


    private void appearLabel(String text) {
        label.setText(text);
        label.setVisible(true);
        disappearLabel();
    }

    private void disappearLabel() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                label.setVisible(false);
            }
        };
        timer.schedule(task, DISAPPEARING_LABEL_DELAY);
    }

    public void showLeaderboard() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/leaderboard.fxml"));
        label.getScene().setRoot(loader.load());
    }

}
