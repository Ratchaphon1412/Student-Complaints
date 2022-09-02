package ku.cs.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.event.ActionEvent;

import javafx.stage.Stage;
import ku.cs.ApplicationController;
import ku.cs.service.DataBase;

import java.io.IOException;
import java.util.Arrays;


public class LoginController {

    @FXML
    private ImageView logoKU;

    @FXML
    private VBox pictureLoginBackground;

    @FXML
    private AnchorPane anchorPaneOnTop;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField passWord;

    private DataBase dataBase = new DataBase();

    @FXML
    private void initialize() throws IOException {
        String logoKUPic = getClass().getResource("/ku/cs/assets/images/LogoKU.png").toExternalForm();
        logoKU.setImage(new Image(logoKUPic));
        FXMLLoader fxmlLoader = new FXMLLoader();
        HBox buttonWindows = (HBox)fxmlLoader.load(getClass().getResource("/ku/cs/components/buttonWindows.fxml"));
        anchorPaneOnTop.getChildren().add(buttonWindows);
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
//    @FXML
//    public void handleLoginAuthentication() throws IOException {
//        String userNameString = userName.getText();
//        String passWordString = passWord.getText();
//        String[] role = {"admin","user","stuff"};
//        if(role[0].equals(dataBase.readFile(userNameString,passWordString))){
//            ApplicationController.goTo("Admin");
//            dataBase.log(userNameString,"admin");
//        }else if(role[1].equals(dataBase.readFile(userNameString,passWordString))){
//            ApplicationController.goTo("User");
//            dataBase.log(userNameString,"user");
//        }else if (role[2].equals(dataBase.readFile(userNameString,passWordString))){
//            ApplicationController.goTo("Stuff");
//            dataBase.log(userNameString,"stuff");
//        }
//
//    }

}
