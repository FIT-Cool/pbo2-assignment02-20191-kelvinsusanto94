package module1.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import module1.entity.Category;
import module1.entity.Item;

import java.net.URL;
import java.util.ResourceBundle;

public class mainformController implements Initializable {

    @FXML
    private ComboBox<Category> myCombobox;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtCategory;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnSavecategory;
    @FXML
    private TableColumn<Item, String> colName;
    @FXML
    private TableColumn<Item, String> colPrice;
    @FXML
    private TableColumn<Item, String> colCategory;
    @FXML
    private TableView<Item> tableview;

    private ObservableList <Category> categories;
    private ObservableList<Item> items;
    private Item selectedItems;

    @FXML
    private TableView<Category> tablecat;
    @FXML
    private TableColumn<Category, String> colC;

    public TextField getTxtName() {
        return txtName;
    }

    public void setTxtName(TextField txtName) {
        this.txtName = txtName;
    }

    public TextField getTxtPrice() {
        return txtPrice;
    }

    public void setTxtPrice(TextField txtPrice) {
        this.txtPrice = txtPrice;
    }

    public TextField getTxtCategoryname() {
        return txtCategory;
    }

    public void setTxtCategoryname(TextField txtCategoryname) {
        this.txtCategory = txtCategoryname;
    }


    @FXML
    private void tableClicked(MouseEvent mouseEvent) {
        selectedItems = tableview.getSelectionModel().getSelectedItem();
        if(selectedItems!=null){
            txtName.setText(selectedItems.getName());
            txtPrice.setText(String.valueOf(selectedItems.getPrice()));
            myCombobox.setValue(selectedItems.getCategory());
            btnSave.setDisable(true);
            btnReset.setDisable(true);
            btnUpdate.setDisable(false);
        }
    }

    @FXML
    private void saveAction(ActionEvent actionEvent) {
        Item i = new Item();
        i.setName(txtName.getText().trim());
        i.setPrice(Double.parseDouble(txtPrice.getText().trim()));
        Category category = new Category();
        category.setName(myCombobox.getValue().toString());
        i.setCategory(category);

        if (!txtName.getText().trim().isEmpty() || myCombobox.getValue() != null || !txtPrice.getText().trim().isEmpty()) {
            int result = 0;
            for (Item _item : items) {
                if (txtName.getText().equalsIgnoreCase(_item.getName().toLowerCase())) {
                    result = 1;
                    break;
                }
            }
            if (result == 1){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Duplicate Item");
                alert.showAndWait();
            } else{
                items.add(i);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Missing field requirement");
            alert.showAndWait();
        }
    }

    @FXML
    private void resetAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Click Ok to delete all data");
        alert.setHeaderText("Confirm Delete");
        alert.showAndWait().ifPresent(( btnType) -> {
            if (btnType == ButtonType.OK) {
                categories.clear();
                items.clear();
            }
        });
    }

    @FXML
    private void updateAction(ActionEvent actionEvent) {
        if (!txtName.getText().trim().isEmpty() || myCombobox.getValue() != null || !txtPrice.getText().trim().isEmpty()) {
            int result = 0;
            for (Item _item : items) {
                if (txtName.getText().equalsIgnoreCase(_item.getName().toLowerCase())) {
                    result += 1;
                }
            }
            if (result > 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Duplicate Item");
                alert.showAndWait();
            } else {
                selectedItems.setName(txtName.getText().trim());
                selectedItems.setPrice(Double.parseDouble(txtPrice.getText().trim()));
                selectedItems.setCategory(myCombobox.getValue());
                txtName.setText("");
                txtName.setText("");
            }
        } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Missing field requirement");
                alert.showAndWait();
        }

        tableview.refresh();
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);
    }

    @FXML
    private void savecategoryAction(ActionEvent actionEvent) {
        Category cat = new Category();
        cat.setName(txtCategory.getText().trim());

        if (txtCategory.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Missing Category Field");
            alert.showAndWait();
        } else{
            int result = 0;
            for (Category _cat : categories) {
                if (txtCategory.getText().equalsIgnoreCase(_cat.getName().toLowerCase())) {
                    result = 1;
                    break;
                }
            }
            if (result == 1){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Duplicate Category");
                alert.showAndWait();
            } else{
                categories.add(cat);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categories = FXCollections.observableArrayList();
        items = FXCollections.observableArrayList();
        myCombobox.setItems(categories);
        tableview.setItems(items);

        btnUpdate.setDisable(true);

        colName.setCellValueFactory(data -> {
            Item i = data.getValue();
            return new SimpleStringProperty(i.getName());
        });
        colPrice.setCellValueFactory(data -> {
            Item i = data.getValue();
            return new SimpleStringProperty(Double.toString(i.getPrice()));
        });
        colCategory.setCellValueFactory(data -> {
            Item i = data.getValue();
            return new SimpleStringProperty(i.getCategory().getName());
        });
    }
}
