package server;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Card;
import models.Placeable;
import models.Shop;
import view.fxmls.wrapperClasses.Leader;

import java.net.URL;
import java.util.ResourceBundle;

public class ShopInServerController implements Initializable {
    public  TableColumn<Placeable, String> cardNameColumn;
    public  TableColumn<Placeable, Integer> costColumn;
    public  TableColumn<Placeable, Integer> numberColumn;
    public  TableView table;
    public static ObservableList<Placeable> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = FXCollections.observableArrayList();
        list.addAll(Shop.getInstance().getShopCards());
        table.setItems(list);
        cardNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
    }



}
