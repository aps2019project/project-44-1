package controller.fxmlControllers;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MapController implements Initializable {

    public GridPane pane;
    public TextField cheat;
    private boolean playing = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        screenShot();
        cheat.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER))
                applyCheat(cheat.getText());
        });
    }

    public void screenShot() {
        Thread thread = new Thread(() -> {
            int index = 0;
            while (playing) {
                try {
                    Robot r = new Robot();
                    Dimension d = new Dimension();
                    d.setSize(pane.getWidth(), pane.getHeight());
                    Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                    BufferedImage Image = r.createScreenCapture(capture);
                    ImageIO.write(Image, "jpg", new File("src\\view\\record\\Shot" + (index++) + ".jpg"));
                    r.setAutoDelay(100);
                } catch (AWTException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        thread.setName("recorder");
        thread.setDaemon(true);
        thread.start();
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    private void applyCheat(String s) {

    }

}
