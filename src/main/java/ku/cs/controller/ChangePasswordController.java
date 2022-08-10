package ku.cs.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ku.cs.ApplicationController;
import ku.cs.service.DataBase;

import java.io.IOException;

public class ChangePasswordController {
    @FXML ImageView changePasswordPic;
    @FXML ImageView Logo;
    @FXML Button close;
    @FXML TextField UserChange;
    @FXML TextField OldPassword;
    @FXML TextField NewPassword;
    private DataBase dataBase = new DataBase();

    @FXML public  void handleAcceptButton (ActionEvent actionEvent){
    String userChange = UserChange.getText();
    String oldPassword = OldPassword.getText();
    String newPassword = NewPassword.getText();

    if (dataBase.changePassword(userChange,oldPassword,newPassword)){
        System.out.println("Password has Changed");
        closeWindows();
    }else{
        System.out.println("Username or Password Incorrect");
        }
    }

    @FXML public void initialize(){
        String changePasswordPicture = getClass().getResource("/ku/cs/assets/images/ChangePasswordPic.png").toExternalForm();
        changePasswordPic.setImage(new Image(changePasswordPicture));

        String logoKU = getClass().getResource("/ku/cs/assets/images/LogoKU.png").toExternalForm();
        Logo.setImage(new Image(logoKU));
    }
    @FXML
    public void closeWindows(){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }



}

