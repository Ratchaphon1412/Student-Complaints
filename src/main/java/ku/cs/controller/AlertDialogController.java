package ku.cs.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class AlertDialogController {

    @FXML
    private ImageView status;

    @FXML
    private Label textStatus;


    @FXML
    private void initialize() throws IOException{
        if (true){
            String status = getClass().getResource("/ku/cs/assets/images/check-mark-icon-png-12.jpg").toExternalForm();
            textStatus.setText("Success fully");
        }
        else {
            textStatus.setText("Failue");
        }
    }

    @FXML
    private void continueButton(){

    }
}
