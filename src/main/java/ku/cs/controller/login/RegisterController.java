package ku.cs.controller.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ku.cs.ApplicationController;
import ku.cs.service.DataBase;
import ku.cs.service.DynamicDatabase;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ku.cs.models.user.User;
import ku.cs.service.ProcessData;


public class RegisterController {
    @FXML
    private TextField userName;
    @FXML
    private PasswordField passWord;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private Label singleFile;
    @FXML
    private ImageView userImage;
    private ProcessData dataBase;
    private String path;
    private File file;

    List<String> listfile;
    @FXML
    public void signUpButton(ActionEvent actionEvent) throws IOException {
        String user = userName.getText();
        String password = passWord.getText();
        String confirmpassword = confirmPassword.getText();
        dataBase = new ProcessData();
        if(!dataBase.checkAccountDuplicate(user)){
            if(password.equals(confirmpassword)){
                if(path != null){
                    User newUser = new User(user,password,path,"user");
                    DynamicDatabase<User> database = new ProcessData<>();
                   boolean checkregister = database.registerAccount(newUser,file);
                   if(checkregister){
                        ApplicationController.goTo("Login");
                   }else{
                        ApplicationController.goToNew("Alert","Failed to register");
                   }
                }else{
                    ApplicationController.goToNew("Alert", "no select picture");
                    System.out.println("no select picture");
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


        if (file != null) {
            singleFile.setText("Selected File: " + file.getAbsolutePath());
            path = file.getAbsolutePath();
            userImage.setImage(new Image(new File(path).toURI().toString()));
        }

    }

}

