package ku.cs.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class ChangePasswordController {
    @FXML ImageView changePasswordPic;

    @FXML ImageView Logo;

    @FXML public void initialize(){
        String changePasswordPicture = getClass().getResource("/ku/cs/assets/images/ChangePasswordPic.png").toExternalForm();
        changePasswordPic.setImage(new Image(changePasswordPicture));

        String logoKU = getClass().getResource("/ku/cs/assets/images/LogoKU.png").toExternalForm();
        Logo.setImage(new Image(logoKU));
    }

}
