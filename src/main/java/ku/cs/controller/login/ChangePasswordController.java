package ku.cs.controller.login;

import animatefx.animation.FadeInUp;
import animatefx.animation.SlideInRight;
import animatefx.animation.ZoomIn;
import animatefx.animation.ZoomOut;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import ku.cs.ApplicationController;
import ku.cs.State;
import ku.cs.service.DataBase;

import java.io.IOException;
import java.util.prefs.Preferences;

public class ChangePasswordController {
    @FXML ImageView changePasswordPic;
    @FXML ImageView Logo;
    @FXML Button close;
    @FXML GridPane rootGridpane;
    @FXML TextField UserChange;
    @FXML TextField OldPassword;
    @FXML TextField NewPassword;

    @FXML Label changPasswordTitle;

    private DataBase dataBase = new DataBase();

    @FXML public  void handleAcceptButton (ActionEvent actionEvent) throws IOException {
    String userChange = UserChange.getText();
    String oldPassword = OldPassword.getText();
    String newPassword = NewPassword.getText();
//
//    if (dataBase.changePassword(userChange,oldPassword,newPassword)){
//        System.out.println("Password has Changed");
//        closeWindows();
//    }else{
//        System.out.println("Username or Password Incorrect");
//        }
        if(dataBase.changePasswordUser(userChange, oldPassword, newPassword)){
            if (userChange == "" || oldPassword == "" || newPassword == ""){
                ApplicationController.goToNew("Alert", "Can't Change password");
                System.out.println("can't change");
            }
            else {
                System.out.println("can");
                closeWindows();
            }
        }else {
            ApplicationController.goToNew("Alert", "Can't Change password");
            System.out.println("can't Change");
        }

    }

    @FXML public void initialize() throws InterruptedException {
        String changePasswordPicture = getClass().getResource("/ku/cs/assets/images/ChangePasswordPic.png").toExternalForm();
        changePasswordPic.setImage(new Image(changePasswordPicture));

        String logoKU = getClass().getResource("/ku/cs/assets/images/LogoKU.png").toExternalForm();
        Logo.setImage(new Image(logoKU));

        //font
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        changPasswordTitle.setFont(font);

        //set Animation
        new FadeInUp(rootGridpane).play();


    }
    @FXML
    public void closeWindows(){
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }



}

