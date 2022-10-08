package ku.cs.controller.components.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private TextField requestField;

    private User user;

    private ProcessData processData;



    public void initialize() {
        user = (User) ApplicationController.getData();
        String requestText = requestField.getText();
        processData = new ProcessData<>();
    }

    @FXML
    private void applyButton(ActionEvent actionEvent) throws IOException {
        String requestText = requestField.getText();
       String countAccess = Integer.toString(user.getCountAccess());
        processData.requestBan(user.getUserName(), requestText, countAccess );
    }

    @FXML
    public void closeWindows(ActionEvent actionEvent){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }
}
