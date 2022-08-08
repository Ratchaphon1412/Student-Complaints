package ku.cs.controller;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import javafx.event.ActionEvent;

import ku.cs.ApplicationController;

import java.io.IOException;


public class LoginController {

    @FXML
    private ImageView logoKU;

    @FXML
    private VBox pictureLoginBackground;
    @FXML
    private void initialize(){
        String logoKUPic = getClass().getResource("/ku/cs/assets/images/LogoKU.png").toExternalForm();
        logoKU.setImage(new Image(logoKUPic));


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
