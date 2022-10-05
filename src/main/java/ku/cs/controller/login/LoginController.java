package ku.cs.controller.login;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import ku.cs.ApplicationController;

import ku.cs.State;
import ku.cs.models.admin.Admin;
import ku.cs.models.staff.Staff;
import ku.cs.models.user.User;

import java.io.IOException;
import java.util.prefs.Preferences;

import ku.cs.service.DynamicDatabase;
import ku.cs.service.ProcessData;


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

    private ProcessData processData;

    @FXML
    private Label forgetLogin;
    @FXML
    private Label welcomeLogin;
    @FXML
    private Label sighUpLogin;





    @FXML
    private void initialize() throws IOException {
        String logoKUPic = getClass().getResource("/ku/cs/assets/images/LogoKU.png").toExternalForm();
        logoKU.setImage(new Image(logoKUPic));
        HBox buttonWindows = (HBox) FXMLLoader.load(getClass().getResource("/ku/cs/components/buttonWindows.fxml"));
        anchorPaneOnTop.getChildren().add(buttonWindows);
        //font
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        forgetLogin.setFont(font);
        welcomeLogin.setFont(font);
        sighUpLogin.setFont(font);
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
    public void infoButton(ActionEvent actionEvent){
        try {
            ApplicationController.goTo("Info");
        }catch (IOException e){
            System.err.println(e);
        }
    }
    @FXML
    public void handleLoginAuthentication() throws IOException {
        String userNameString = userName.getText();
        String passWordString = passWord.getText();
        processData = new ProcessData<>();

        //check has account
        if(processData.checkAccount(userNameString)){
            //check ban
            if(!processData.checkBan(userNameString)){
                //check role
                switch(processData.checkRole(userNameString)){
                    case "admin"->{
                        DynamicDatabase<Admin> database = new ProcessData<>();
                        Admin admin = database.login(userNameString,passWordString);
                        if(admin != null){
                            System.out.println("test");
                            ApplicationController.goTo("Admin",admin);
                        }else{
                            ApplicationController.goToNew("Alert", "wrong password");
                            System.out.println("wrong password");
                        }
                        break;
                    }
                    case "user"->{
                        DynamicDatabase<User> database = new ProcessData<>();
                        User user = database.login(userNameString,passWordString);

                        if(user != null){
                            if(user.isBan()){
                                user.setCountAccess();
                                userName.clear();
                                passWord.clear();
                            }
                            else {
                                ApplicationController.goTo("User", user);
                            }
                        }else{
                            ApplicationController.goToNew("Alert", "wrong password");
                            System.out.println("wrong password user");
                        }
                        break;
                    }
                    case "staff"->{
                        DynamicDatabase<Staff> database = new ProcessData<>();
                        Staff staff = database.login(userNameString,passWordString);
                        if(staff != null){
                            ApplicationController.goTo("Staff",staff);
                        }else{
                            ApplicationController.goToNew("Alert", "wrong password");
                            System.out.println("wrong password staff");
                        }
                        break;
                    }
                }

            }else{
                //ทำหน้าขออันแบน
                ApplicationController.goTo("AlertRequest", "You are banned");
                System.out.println("banned");
            }
        }else{
            ApplicationController.goToNew("Alert", "You don't have account");
            System.out.println("no account in system");
        }
    }
}
