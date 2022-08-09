package ku.cs.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.event.ActionEvent;

import javafx.stage.Stage;
import ku.cs.ApplicationController;

import java.io.IOException;


public class LoginController {

    @FXML
    private ImageView logoKU;

    @FXML
    private VBox pictureLoginBackground;

//    @FXML
//    private GridPane gridLeftSideOnTop;
    @FXML
    private AnchorPane anchorPaneOnTop;
    @FXML
    private void initialize() throws IOException {
        String logoKUPic = getClass().getResource("/ku/cs/assets/images/LogoKU.png").toExternalForm();
        logoKU.setImage(new Image(logoKUPic));
        FXMLLoader fxmlLoader = new FXMLLoader();
        ButtonWindowsController buttonWindowsController = (ButtonWindowsController) fxmlLoader.getController();

        HBox buttonWindows = (HBox)fxmlLoader.load(getClass().getResource("/ku/cs/components/buttonWindows.fxml"));
        anchorPaneOnTop.getChildren().add(buttonWindows);
//
//        Stage stageParent = (Stage) anchorPaneOnTop.getScene().getWindow();
//        buttonWindowsController.setStageParent(stageParent);




    }








    @FXML
    public void  handleChangePasswordButton(ActionEvent actionEvent) {
        try {
            ApplicationController.goToNew("changePassword");
        } catch (IOException e) {
            System.err.println(e);

        }

    }

    @FXML
    public void handleRegisterButton(ActionEvent actionEvent) {
        try {
            ApplicationController.goTo("Register");
        } catch (IOException e) {
            System.err.println(e);


        }
    }


}
