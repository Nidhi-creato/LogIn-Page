package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;


public class logInController implements Initializable {

    @FXML
    private Button button_logout;

    @FXML
    private RadioButton rb_easy;
    @FXML
    private RadioButton rb_intermediate;
    @FXML
    private RadioButton rb_hard;
    @FXML
    private RadioButton rb_hackerrank;
    @FXML
    private RadioButton rb_leetcode;
    @FXML
    private RadioButton rb_codeforces;
    @FXML
    private RadioButton rb_codechef;
    @FXML
    private RadioButton rb_others;

    @FXML
    private Label label_welcome;


    public logInController() {
    }
    public void getButton(ActionEvent event)
    {


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group = new ToggleGroup();

        rb_easy.setUserData("Easy");
        rb_easy.setToggleGroup(group);
        rb_easy.setSelected(true);
        rb_intermediate.setUserData("Intermediate");
        rb_intermediate.setToggleGroup(group);
        rb_hard.setUserData("Hard");
        rb_hard.setToggleGroup(group);
        group.selectedToggleProperty().addListener((observable, oldVal, newVal) -> System.out.println(newVal + " was selected"));
//        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
//            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
//
//                if (group.getSelectedToggle() != null) {
//                    System.out.println(group.getSelectedToggle().getUserData().toString());
//                    // Do something here with the userData of newly selected radioButton
//
//                }
//
//            }
//        });
        ToggleGroup group2 = new ToggleGroup();
        rb_hackerrank.setUserData("Hackerrank");
        rb_hackerrank.setToggleGroup(group2);
        rb_leetcode.setUserData("Leetcode");
        rb_leetcode.setToggleGroup(group2);
        rb_codeforces.setUserData("Codeforces");
        rb_codeforces.setToggleGroup(group2);
        rb_codechef.setUserData("Codechef");
        rb_codechef.setToggleGroup(group2);
        rb_others.setUserData("Others");
        rb_others.setToggleGroup(group2);
        group2.selectedToggleProperty().addListener((observable, oldVal, newVal) -> System.out.println(newVal + " was selected"));
//        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
//            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
//
//                if (group.getSelectedToggle() != null) {
//                    System.out.println(group2.getSelectedToggle().getUserData().toString());
//                    // Do something here with the userData of newly selected radioButton
//
//                }
//
//            }
//        });
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            databaseUtil.changeScene(actionEvent,"sample.fxml","log in!",null,null,null);
            }
        });

    }

    public void setUserInformation(String username, String institution) {
        label_welcome.setText("welcome"+ username);

    }


}


