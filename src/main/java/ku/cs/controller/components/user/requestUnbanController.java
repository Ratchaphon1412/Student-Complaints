package ku.cs.controller.components.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ku.cs.ApplicationController;
import ku.cs.models.admin.Admin;
import ku.cs.models.user.User;
import ku.cs.service.ProcessData;

import java.io.IOException;

public class requestUnbanController {

    @FXML
    private Button close;

    @FXML
    private TextArea requestField;

    @FXML
    private TextField userName;

    @FXML private Label error;


    private User user;

    private ProcessData processData;



    public void initialize() {
        user = (User) ApplicationController.getData();

        processData = new ProcessData<>();
    }

    @FXML
    private void applyButton(ActionEvent actionEvent) throws IOException {
        if (userName.getText() != "" && userName.getText().equals(user.getEmail())) {
            if(requestField.getText() != "") {
                String requestText = requestField.getText();
                String userNameText = userName.getText();
                processData.requestBan(userNameText, requestText);
                closeWindows();
            }else {error.setText("โปรดใส่ข้อความ");}
        }else {error.setText("โปรดใส่ email ให้ถูกต้อง");}

    }

    @FXML
    public void closeWindows(){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }
}
