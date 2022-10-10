package ku.cs.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ku.cs.ApplicationController;
import ku.cs.models.user.User;

import java.io.IOException;

public class alertRequestUnbanController {
    @FXML
    private ImageView status;

    @FXML
    private Label textStatus;

    @FXML
    private Button close;
    private User user;


    @FXML
    private void initialize() throws IOException {
        user = (User) ApplicationController.getData();
        String text = ApplicationController.getRequestText();
        String pathPicture = getClass().getResource("/ku/cs/assets/images/error-icon-4.png").toExternalForm();
        status.setImage(new Image(pathPicture));
        textStatus.setText(text);
    }

    @FXML
    public void requestButton(ActionEvent event) throws IOException {
        ApplicationController.goToNew("RequestUnban",user);
        closeWindows();
    }
    @FXML
    public void closeWindows(){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();


}
