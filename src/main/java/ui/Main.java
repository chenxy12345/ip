package ui;

import elmacho.Elmacho;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Elmacho elmacho = new Elmacho();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Elmacho");

            stage.setMinHeight(300);
            stage.setMinWidth(550);

            MainWindow controller = fxmlLoader.getController();
            controller.setElmacho(elmacho);
            controller.displayWelcomeMessage();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
