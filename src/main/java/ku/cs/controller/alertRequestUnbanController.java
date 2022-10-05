package ku.cs.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ku.cs.ApplicationController;

import java.io.IOException;

public class alertRequestUnbanController {
    @FXML
    private ImageView status;

    @FXML
    private Label textStatus;

    @FXML
    private Button close;

    @FXML
    private void initialize() throws IOException {
        String text = ApplicationController.getDataText();

        String pathPicture = getClass().getResource("/ku/cs/assets/images/error-icon-4.png").toExternalForm();
        status.setImage(new Image(pathPicture));
        textStatus.setText(text);
    }
    @FXML
    public void closeWindows(){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void requestButton(ActionEvent event) throws IOException {
        ApplicationController.goTo("Request");
    }

}