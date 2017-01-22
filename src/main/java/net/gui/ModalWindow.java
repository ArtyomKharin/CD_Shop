package net.gui;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.gui.Main;
import net.gui.controller.AddingController;
import net.gui.controller.EditController;

/**
 * Created by EvSpirit on 15.12.2016.
 */
public class ModalWindow {
    public static boolean checker=false;
    public void newWindow(String title, String mode, String id){
        boolean modal=true;
        if(id!="") {
            AddingController.id = Integer.parseInt(id);
        }
        String fxmlFile;
        switch (title){
            case("login"):fxmlFile = "/fxml/authorization.fxml";
                break;
            case("Adding"):fxmlFile = "/fxml/adding.fxml";
                modal=false;
                AddingController.mode=mode;
                break;
            default:return;
        }
        FXMLLoader loader = new FXMLLoader();
        try {
            Parent root1=FXMLLoader.<Parent>load(getClass().getResource(fxmlFile));
            Stage window=new Stage();
            if(modal) {
                window.initModality(Modality.APPLICATION_MODAL);
            }else{
                window.initModality(Modality.NONE);
            }
            Pane root=new Pane();
            Scene scene= new Scene(root1);
            window.setScene(scene);
            window.setTitle(title);

            /*
            window.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    if(!checker){
                        event.consume();
                    }
                }
            });
            */

                window.showAndWait();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }
    public void newWindow(String title, String mode, int id){
        EditController.id=id;
        EditController.mode=mode;
        String fxmlFile= "/fxml/editing.fxml";

        FXMLLoader loader = new FXMLLoader();
        try {
            Parent root=FXMLLoader.<Parent>load(getClass().getResource(fxmlFile));
            Stage window=new Stage();
            window.initModality(Modality.NONE);
            Scene scene= new Scene(root);
            window.setScene(scene);
            window.setTitle(title);

            /*
            window.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    if(!checker){
                        event.consume();
                    }
                }
            });
            */

            window.showAndWait();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
