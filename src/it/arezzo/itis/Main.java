package it.arezzo.itis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("calculator.fxml"));
        primaryStage.setTitle("Calcolatrice");
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.setResizable(false); //la finestra non si ridimensiona
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
