package net.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.gui.controller.AddingController;
import net.gui.controller.ChoosingController;
import net.gui.controller.EditController;

/**
 * Created by EvSpirit on 15.12.2016.
 */
public class ChoiceWindow {
    public static boolean checker=false;
    public void newWindow(String mode,String calledBy){
        ChoosingController.mode=mode;
        ChoosingController.calledBy=calledBy;
        String fxmlFile = "/fxml/choosing.fxml";
        FXMLLoader loader = new FXMLLoader();
        try {
            Parent root1=FXMLLoader.<Parent>load(getClass().getResource(fxmlFile));
            Stage window=new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            Pane root=new Pane();
            Scene scene= new Scene(root1);
            window.setScene(scene);
            window.setTitle("Choosing of "+ mode);

                window.showAndWait();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

}
