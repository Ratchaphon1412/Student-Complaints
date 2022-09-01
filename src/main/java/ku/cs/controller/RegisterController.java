package ku.cs.controller;

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

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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
    private DataBase dataBase = new DataBase();
    private String path;
    private File file;

    List<String> listfile;
    @FXML
    public void signUpButton(ActionEvent actionEvent) {
        String user = userName.getText();
        String password = passWord.getText();
        String confirmpassword = confirmPassword.getText();
//        if(dataBase.signUp(user,password,"user",path,file)){
//            System.out.println("finish");
//            try {
//                ApplicationController.goTo("Login");
//            } catch (IOException e) {
//                System.err.println(e);
//            }
//        }
//        else{
//            System.out.println("ชื่อซ้ำโว้ย");
//            System.out.println("รหัสไม่เหมือนกัน");
//        }

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


