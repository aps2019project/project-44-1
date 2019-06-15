package controller.fxmlControllers;

import controller.GameController;
import javafx.animation.AnimationTimer;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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
    }

    public void handleSubmit() {
        submitButton.setOnAction(event -> loginAction());
    }

    private void loginAction() {
        if (!gameController.isAlive())
            initializeThread();
        synchronized (gameController) {
            gameController.notify();
        }
        try {
            gameController.getGraphicState(usernameTextField.getText(), passwordTextField.getText(),
                    submitButton.getText().equals("LOG IN"));
            Thread.sleep(5);            //it's a trick to wait for receive logic process results
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    private void initializeThread() {
        gameController.setName("gameController");
        gameController.setDaemon(true);
        gameController.getGraphicState(usernameTextField.getText(), passwordTextField.getText(),
                submitButton.getText().equals("LOG IN"));
        gameController.start();
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

    public void showLeaderboard(){

    }

}
