package module1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import module1.controller.mainformController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/MainForm.fxml"));
        primaryStage.setTitle("Praktikum minggu #2");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}