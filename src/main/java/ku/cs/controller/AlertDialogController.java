package ku.cs.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import ku.cs.ApplicationController;

import java.io.IOException;

public class AlertDialogController {

    @FXML
    private ImageView status;

    @FXML
    private Label textStatus;

    @FXML
    private AnchorPane anchorPane;


    @FXML
    private void initialize() throws IOException{
        String text = ApplicationController.getDataText();

            String pathPicture = getClass().getResource("/ku/cs/assets/images/error-icon-4.png").toExternalForm();
            status.setImage(new Image(pathPicture));
            textStatus.setText(text);

        HBox buttonWindows = (HBox) FXMLLoader.load(getClass().getResource("/ku/cs/components/buttonWindows.fxml"));
        anchorPane.getChildren().add(buttonWindows);


    }

    @FXML
    private void continueButton(){

    }
}
