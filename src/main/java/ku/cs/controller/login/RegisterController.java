package ku.cs.controller.login;

import animatefx.animation.SlideInRight;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import ku.cs.ApplicationController;
import ku.cs.State;
import ku.cs.service.DataBase;
import ku.cs.service.DynamicDatabase;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import ku.cs.models.user.User;
import ku.cs.service.ProcessData;


public class RegisterController {
    @FXML
    private TextField userName;
    @FXML
    private TextField email;
    @FXML
    private PasswordField passWord;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private Label singleFile;
    @FXML
    private Label uploadPicture;
    @FXML
    private Label sighUpTitle;
    @FXML
    private ImageView userImage;
    @FXML
    private GridPane rootGridpane;
    private ProcessData dataBase;
    private String path;
    private File file;

    List<String> listfile;

    @FXML
    public void initialize() throws IOException{
        //font
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        uploadPicture.setFont(font);
        sighUpTitle.setFont(font);
        singleFile.setFont(font);

    }
    @FXML
    public void signUpButton(ActionEvent actionEvent) throws IOException {
        String user = userName.getText();
        String emails = email.getText();
        String password = passWord.getText();
        String confirmpassword = confirmPassword.getText();
        dataBase = new ProcessData();
        if(!dataBase.checkAccountDuplicate(user)){
            if(password.equals(confirmpassword)){
                if(path != null){
                    User newUser = new User(emails,user,password,path,"user");
                    DynamicDatabase<User> database = new ProcessData<>();
                   boolean checkregister = database.registerAccount(newUser,file,"user");
                   if(checkregister){
                        ApplicationController.goTo("Login");
                   }else{
                        ApplicationController.goToNew("Alert","Failed to register");
                   }
                }else{//แก้ให้ใส่ภาพตั้งต้น

                    User newUser = new User(emails,user,password,path,"user");
                    DynamicDatabase<User> database = new ProcessData<>();
                    boolean checkregister = database.registerAccount(newUser,file,"user");
                    if(checkregister){
                        ApplicationController.goTo("Login");
                    }




                }
            }else{
                ApplicationController.goToNew("Alert", "password not correct");
                System.out.println("passWord not correct");
            }
        }else{
            ApplicationController.goToNew("Alert", "Have Account in Database");
            System.out.println("Have Account in Database");
        }
    }

    @FXML
    public void fileChooser(ActionEvent actionEvent) {
        FileChooser choosefile = new FileChooser();
        listfile = new ArrayList<>();
        listfile.add("*.jpg");
        listfile.add("*.png");
        listfile.add("*jpeg");
        choosefile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Picture", listfile));


        file = choosefile.showOpenDialog(null);

        userImage.setImage(new Image(new File(getClass().getResource("/ku/cs/assets/images/download.png").toExternalForm()).toURI().toString()));
        System.out.println(path);

        if (file != null) {
            singleFile.setText("Selected File: " + file.getAbsolutePath());
            path = file.getAbsolutePath();
            userImage.setImage(new Image(new File(path).toURI().toString()));
        }

    }

    @FXML
    public void handleCancleSettingButton(ActionEvent actionEvent) {
        try {
            ApplicationController.goTo("Login");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}


