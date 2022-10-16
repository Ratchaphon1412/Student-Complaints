package ku.cs.controller.login;


import animatefx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import ku.cs.ApplicationController;

import ku.cs.State;
import ku.cs.models.admin.Admin;
import ku.cs.models.staff.Staff;
import ku.cs.models.user.User;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import ku.cs.service.DynamicDatabase;
import ku.cs.service.ProcessData;

import javax.swing.*;
import java.awt.Desktop;

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
    private GridPane mainLoginPage;





    @FXML
    private void initialize() throws IOException, InterruptedException {
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
    public void pdfButtin(ActionEvent actionEvent){
        try{
            File file = new File("C:\\CSV\\fileProjectJavacsv.pdf");
            if(file.exists()){
                if(Desktop.isDesktopSupported()){
                    Desktop.getDesktop().open(file);
                }
                else {
                    System.out.println("Not Supported");
                }
            }
            else {
                System.out.println("File Not Exist");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    @FXML
    public void handleLoginAuthentication() throws IOException {
        String userNameString = userName.getText();
        String passWordString = passWord.getText();
        processData = new ProcessData<>();

        //check has account
        if(processData.checkAccount(userNameString)){
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
                    if(user !=null){

                        if(!processData.checkBan(userNameString)){

                            ApplicationController.goTo("User", user);

                        }else{
                            user.setCountAccess();
                            processData.refreshContAccess(user.getEmail(),user.getCountAccess() );

                            //ทำหน้าขออันแบน
                            ApplicationController.goToNew("AlertRequest",user, "you are banned");
                            System.out.println("banned");
                            System.out.println(user.getCountAccess());
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
            //check ban

        }else{
            ApplicationController.goToNew("Alert", "You don't have account");
            System.out.println("no account in system");
        }
    }
}
