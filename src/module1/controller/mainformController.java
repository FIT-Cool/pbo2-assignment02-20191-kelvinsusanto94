package module1.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import module1.entity.Category;
import module1.entity.Item;

public class mainformController {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtCategoryname;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colPrice;
    @FXML
    private TableColumn colCategory;
    @FXML
    private TableView tableview;

    private ObservableList<Category> categories;
    private ObservableList<Item> items;

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
        return txtCategoryname;
    }

    public void setTxtCategoryname(TextField txtCategoryname) {
        this.txtCategoryname = txtCategoryname;
    }


    @FXML
    private void tableClicked(MouseEvent mouseEvent) {
        
    }

    @FXML
    private void saveAction(ActionEvent actionEvent) {
    }

    @FXML
    private void resetAction(ActionEvent actionEvent) {
    }

    @FXML
    private void updateAction(ActionEvent actionEvent) {
    }

    @FXML
    private void savecategoryAction(ActionEvent actionEvent) {
        Category c = new Category();
        c.setName(txtCategoryname.getText().trim());
        categories.add(c);
    }
}
