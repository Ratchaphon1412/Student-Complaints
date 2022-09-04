package ku.cs.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import ku.cs.ApplicationController;

import ku.cs.models.admin.Admin;
import ku.cs.models.stuff.Stuff;
import ku.cs.models.user.User;
import ku.cs.service.DataBase;
import java.io.IOException;
import ku.cs.models.Account;
import ku.cs.service.DynamicDatabase;


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

    private DataBase dataBase;

    @FXML
    private void initialize() throws IOException {
        String logoKUPic = getClass().getResource("/ku/cs/assets/images/LogoKU.png").toExternalForm();
        logoKU.setImage(new Image(logoKUPic));
        HBox buttonWindows = (HBox) FXMLLoader.load(getClass().getResource("/ku/cs/components/buttonWindows.fxml"));
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
    @FXML
    public void handleLoginAuthentication() throws IOException {
        String userNameString = userName.getText();
        String passWordString = passWord.getText();
        dataBase = new DataBase();

        //check has account
        if(dataBase.checkAccount(userNameString)){
            //check ban
            if(!dataBase.checkBan(userNameString)){
                //check role
                switch(dataBase.checkRole(userNameString)){
                    case "admin"->{
                        DynamicDatabase<Admin> database = new DataBase<>();
                        Admin admin = database.login(userNameString,passWordString);
                        if(admin != null){
                            ApplicationController.goTo("Admin",admin);
                        }else{
                            System.out.println("wrong password");
                        }
                        break;
                    }
                    case "user"->{
                        DynamicDatabase<User> database = new DataBase<>();
                        User user = database.login(userNameString,passWordString);
                        if(user != null){
                            ApplicationController.goTo("User",user);
                        }else{
                            System.out.println("wrong password");
                        }
                        break;
                    }
                    case "stuff"->{
                        DynamicDatabase<Stuff> database = new DataBase<>();
                        Stuff stuff = database.login(userNameString,passWordString);
                        if(stuff != null){
                            ApplicationController.goTo("Stuff",stuff);
                        }else{
                            System.out.println("wrong password");
                        }
                        break;
                    }
                }

            }else{
                System.out.println("banned");
            }
        }else{
            System.out.println("no account in system");
        }

    }

}
