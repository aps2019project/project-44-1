package controller.fxmlControllers;

import Main.Main;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logInButton.setOnAction(event -> submitButton.setText("LOG IN"));
        signUpButton.setOnAction(event -> submitButton.setText("SIGN UP"));
    }

    public void handleSubmit() {
        submitButton.setOnAction(event -> {
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();
            if (submitButton.getText().equals("LOG IN")) {
                Main.getStage().getScene().setRoot(Main.getMainMenu());
            } else {

            }

        });
    }

}
