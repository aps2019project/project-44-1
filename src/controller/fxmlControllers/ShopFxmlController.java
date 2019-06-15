package controller.fxmlControllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import models.Hero;
import models.Placeable;
import models.Shop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShopFxmlController implements Initializable {

    public GridPane rootGridPane;
    public ScrollPane scrollPane;

    private static class CardContainer extends Pane {
        private Label label;
        private VBox vBox;
        private ImageView imageView = new ImageView(new Image(new FileInputStream("view/images/neutral_unit@2x.png"), 30, 40, false, false));

        public CardContainer(Label text) throws FileNotFoundException {
            vBox = new VBox();
            this.label = text;


        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Placeable> cards = Shop.getInstance().getCards();
        ImageView imageView = new ImageView();
        scrollPane.setPannable(true);
        FlowPane flowPane = new FlowPane();
        scrollPane.setContent(flowPane);
        flowPane.setOrientation(Orientation.VERTICAL);
        try {
            Image image = new Image(new FileInputStream("src/view/images/general_f1third.png"),150,200,false,false);
            imageView.setImage(image);
        } catch (FileNotFoundException ignored) {
        }
        for (Placeable placeable : cards) {
            if (placeable instanceof Hero) {
                VBox vBox = new VBox();
                ImageView imageView1 = new ImageView(imageView.getImage());
                Label label = new Label(placeable.getName());


                vBox.getChildren().addAll(imageView1,label);
                flowPane.getChildren().add(vBox);

            }
        }


    }

}
