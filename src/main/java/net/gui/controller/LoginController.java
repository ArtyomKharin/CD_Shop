package net.gui.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.gui.ModalWindow;
import net.gui.services.UserService;
import net.gui.utils.Validator;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by EvSpirit on 20.12.2016.
 */
public class LoginController implements Initializable {
    UserService service=new UserService();
    @FXML
    private Button loginButton;
    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField passTextField;

    public void initialize(URL url, ResourceBundle rb){
        loginTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (loginTextField.getText().length() > 16) {
                    String s = loginTextField.getText().substring(0, 16);
                    loginTextField.setText(s);
                }

                if(loginTextField.getText().length()>0&&Validator.validationString.indexOf(loginTextField.getText().charAt(loginTextField.getText().length()-1))==-1){
                    String s = loginTextField.getText().substring(0,loginTextField.getText().length() - 1);
                    loginTextField.setText(s);
                }
            }
        });
        passTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (passTextField.getText().length() > 16) {
                    String s = passTextField.getText().substring(0, 16);
                    passTextField.setText(s);
                }
            }
        });

    }
    public void login(ActionEvent e){
        try {

            if (loginTextField.getText().length() < 6) throw new Exception("Login should contain at least 6 symbols");
            if (passTextField.getText().length() < 6) throw new Exception("Password should contain at least 6 symbols");
            if (service.Authorization(loginTextField.getText(), passTextField.getText())) {
                ModalWindow.checker = true;
                close();
            } else {
                loginTextField.clear();
                passTextField.clear();
                throw new Exception("Wrong Login or password");
            }
        }
        catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(ex.getMessage());
            alert.setContentText("Try again");
            alert.showAndWait();
        }
    }
    public void close(){
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }
}
