package net.gui;

/**
 * Created by EvSpirit on 14.12.2016.
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.gui.utils.*;

public class Main extends Application {
    public static Stage primaryStage;
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        String fxmlFile = "/fxml/sample.fxml";
        FXMLLoader loader = new FXMLLoader();
         Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        stage.setTitle("CD Shop");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void stop(){
        HibernateSessionManager.shutdown();
    }

}