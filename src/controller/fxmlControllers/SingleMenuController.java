package controller.fxmlControllers;

import Main.Main;
import client.RequestSender;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import server.Environment;
import server.Request;
import server.RequestType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SingleMenuController implements Initializable {
    public ImageView first;
    public ImageView second;
    public ImageView third;
    public Label fLabel;
    public Label sLabel;
    public Label tLabel;
    public Button back;
    private static final int SINGLE1 = 1;
    private static final int SINGLE2 = 2;
    private static final int SINGLE3 = 3;
    private MapController controller = new MapController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        first.setImage(new Image("view/images/generalPortrait/general_portrait_image_f1.png"));
        second.setImage(new Image("view/images/generalPortrait/general_portrait_image_f2.png"));
        third.setImage(new Image("view/images/generalPortrait/general_portrait_image_f3.png"));
        fLabel.setText("DeathMatch\ndivsefid");
        sLabel.setText("SaveFlag\nzahhak");
        tLabel.setText("CaptureFlag\narash");
        first.setOnMouseClicked(mouseEvent -> startBattle(SINGLE1));
        second.setOnMouseClicked(mouseEvent -> startBattle(SINGLE2));
        third.setOnMouseClicked(mouseEvent -> startBattle(SINGLE3));
        back.setOnAction(actionEvent -> MainMenuController.loadPage("/view/fxmls/battleMenu.fxml"));
    }

    private void startBattle(int state) {
        Request request = new Request(Environment.BATTLE);
        request.setState(state);
        request.setRequestType(RequestType.SINGLE_PLAYER);
        RequestSender.getInstance().sendRequest(request);
        loadScreen();
    }

    private void loadScreen() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/map.fxml"));
        try {
            Main.getStage().getScene().setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        loader.setController(controller);
    }

}
