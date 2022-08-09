package ku.cs.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.File;
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

    List<String> listfile;
    @FXML
    public void sighUpButton(ActionEvent actionEvent) {
        String user = userName.getText();
        String password = passWord.getText();
        String confirmpassword = confirmPassword.getText();
        System.out.println(user);
        System.out.println(password);
        System.out.println(confirmpassword);
    }

    @FXML
    public void fileChooser(ActionEvent actionEvent) {
        FileChooser choosefile = new FileChooser();
        listfile = new ArrayList<>();
        listfile.add("*.jpg");
        listfile.add("*.png");
        choosefile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Picture", listfile));


        File file = choosefile.showOpenDialog(null);

        if (file != null) {
            singleFile.setText("Selected File: " + file.getAbsolutePath());
        }
    }


}


