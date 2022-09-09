package ku.cs.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.ApplicationController;

import java.io.IOException;

public class AlertDialogController {

    @FXML
    private ImageView status;

    @FXML
    private Label textStatus;


    @FXML
    private void initialize() throws IOException{
        String text = ApplicationController.getDataText();

            String pathPicture = getClass().getResource("/ku/cs/assets/images/error-icon-4.png").toExternalForm();
            status.setImage(new Image(pathPicture));
            textStatus.setText(text);


    }

    @FXML
    private void continueButton(){

    }
}
