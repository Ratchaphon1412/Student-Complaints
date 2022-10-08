package ku.cs.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ku.cs.ApplicationController;

import java.io.IOException;

public class AlertDialogController {

    @FXML
    private ImageView status;

    @FXML
    private Label textStatus;

    @FXML
    private Button close;

    @FXML
    private AnchorPane anchorPane;


    @FXML
    private void initialize() throws IOException{
        String text = ApplicationController.getDataText();

            String pathPicture = getClass().getResource("/ku/cs/assets/images/error-icon-4.png").toExternalForm();
            status.setImage(new Image(pathPicture));
            textStatus.setText(text);
    }

    @FXML
    private void continueButton(){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void closeWindows(){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }


}
