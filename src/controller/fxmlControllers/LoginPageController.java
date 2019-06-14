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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logInButton.setOnAction(event -> submitButton.setText("LOG IN"));
        signUpButton.setOnAction(event -> submitButton.setText("SIGN UP"));
    }

    public void handleSubmit() {
        submitButton.setOnAction(event -> loginAction());
    }

    private void loginAction() {
        System.out.println("hello");
//        GameController gameController = GameController.getInstance();
//        gameController.getGraphicState(usernameTextField.getText(), passwordTextField.getText(),
//                submitButton.getText().equals("LOG IN"));
//        gameController.start();
//        String labelText = gameController.getLabelText();
//        if (labelText != null)
//            appearLabel(labelText);
    }

    private void appearLabel(String text) {
        label.setText(text);
        label.setVisible(true);
    }

}
