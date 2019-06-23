package controller.fxmlControllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import models.Game;
import models.Placeable;
import models.Shop;

import java.net.URL;
import java.util.ResourceBundle;

public class ShopFxmlController implements Initializable {

    public Button back;
    public ScrollPane scrollPane;

//    private static class CardContainer extends Pane {
//        private Label label;
//        private VBox vBox;
//        private ImageView imageView = new ImageView(new Image(new FileInputStream("view/images/neutral_unit@2x.png"), 30, 40, false, false));
//
//        public CardContainer(Label text) throws FileNotFoundException {
//            vBox = new VBox();
//            this.label = text;
//        }
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        ArrayList<Placeable> cards = Shop.getInstance().getCards();
//        ImageView imageView = new ImageView();
//        scrollPane.setPannable(true);
//        FlowPane flowPane = new FlowPane();
//        scrollPane.setContent(flowPane);
//        flowPane.setOrientation(Orientation.VERTICAL);
//        try {
//            Image image = new Image(new FileInputStream("src/view/images/general_f1third.png"),150,200,false,false);
//            imageView.setImage(image);
//        } catch (FileNotFoundException ignored) {
//        }
//        for (Placeable placeable : cards) {
//            if (placeable instanceof Hero) {
//                VBox vBox = new VBox();
//                ImageView imageView1 = new ImageView(imageView.getImage());
//                Label label = new Label(placeable.getName());
//
//
//                vBox.getChildren().addAll(imageView1,label);
//                flowPane.getChildren().add(vBox);
//
//            }
//        }
        back.setOnAction(actionEvent -> Game.getInstance().loadPage(back, "/view/fxmls/mainMenu.fxml"));
        Shop shop = Shop.getInstance();
        for (Placeable c : shop.getCards()) {
            scrollPane.setContent(craftGraphics(c.getUrl()));
        }
    }

    private AnchorPane craftGraphics(final String url) {
        AnchorPane anchorPane = new AnchorPane();
        return anchorPane;
    }

}
