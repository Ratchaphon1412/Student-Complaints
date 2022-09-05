package ku.cs.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ku.cs.service.DataBase;
import ku.cs.service.DynamicDatabase;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import ku.cs.models.user.User;


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
    private DataBase dataBase;
    private String path;
    private File file;

    List<String> listfile;
    @FXML
    public void signUpButton(ActionEvent actionEvent) {
        String user = userName.getText();
        String password = passWord.getText();
        String confirmpassword = confirmPassword.getText();
        if(dataBase.checkAccount(user)){
            if(password.equals(confirmpassword)){
                if(path != null){
                    User newUser = new User(user,password,path,"user","");
                    DynamicDatabase<User> database = new DataBase<>();
                    database.registerAccount(newUser);
                }else{
                    System.out.println("no select picture");
                }
            }else{
                System.out.println("passWord not correct");
            }
        }else{
            System.out.println("Have Account in Database");
        }
    }

    @FXML
    public void fileChooser(ActionEvent actionEvent) {
        FileChooser choosefile = new FileChooser();
        listfile = new ArrayList<>();
        listfile.add("*.jpg");
        listfile.add("*.png");
        choosefile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Picture", listfile));


        file = choosefile.showOpenDialog(null);


        if (file != null) {
            singleFile.setText("Selected File: " + file.getAbsolutePath());
            path = file.getAbsolutePath();
            System.out.println(path);
            userImage.setImage(new Image(new File(path).toURI().toString()));
        }

    }

}


