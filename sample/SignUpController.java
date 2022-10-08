package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button button_signup;
    @FXML
    private Button button_login;

    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_Email;
    @FXML
    private TextField tf_Institution;
    @FXML
    private TextField tf_newPassword;
    @FXML
    private TextField tf_confirm;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tf_username.getText().trim().isEmpty() && !tf_newPassword.getText().trim().isEmpty()
                &&!tf_Email.getText().trim().isEmpty() && !tf_Institution.getText().trim().isEmpty()
                && tf_newPassword.getText().trim().equals(tf_confirm.getText().trim())){
                    databaseUtil.signUpUser(event,tf_username.getText(),tf_Email.getText(),tf_Institution.getText(),tf_newPassword.getText());
                }else {
                    System.out.println("please fill in all information");
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("please fill in all information to sign up");
                    alert.show();
                }
            }
        });

        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                databaseUtil.changeScene(event,"sample.fxml","Log in!",null,null,null);
            }
        });
    }
}

