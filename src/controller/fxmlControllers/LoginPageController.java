package controller.fxmlControllers;

import controller.GameController;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {
    public GridPane root;
    public Button logInButton;
    public Button signUpButton;
    public Button submitButton;
    public PasswordField passwordTextField;
    public TextField usernameTextField;
    public Label label;
    private final GameController gameController = GameController.getInstance();

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
            Thread.yield();
            gameController.notify();
        }
        try {
            Thread.sleep(3);            //it's a trick to wait for receive logic process results
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
    }

    private void initializeThread() {
        gameController.setName("gameController");
        gameController.setDaemon(true);
        gameController.setPriority(Thread.MAX_PRIORITY);
        gameController.getGraphicState(usernameTextField.getText(), passwordTextField.getText(),
                submitButton.getText().equals("LOG IN"));
        gameController.start();
    }

}
