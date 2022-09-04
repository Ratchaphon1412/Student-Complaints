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
        String[] role = {"admin","user","stuff"};
        dataBase = new DataBase();

        //check has account
        if(dataBase.checkAccount(userNameString)){
            if(!dataBase.checkBan(userNameString)){
                switch (dataBase.checkRole(userNameString)) {
                    case "admin" -> {
                        DynamicDatabase<Admin> dynamicDatabaseAdmin = new DataBase<>();
                        Admin admin = dynamicDatabaseAdmin.login(userNameString, passWordString);
                        ApplicationController.goTo("Admin", admin);
                    }
                    case "user" -> {
                        DynamicDatabase<User> dynamicDatabaseUser = new DataBase<>();
                        User user = dynamicDatabaseUser.login(userNameString, passWordString);
                        ApplicationController.goTo("User", user);
                    }
                    case "stuff" -> {
                        DynamicDatabase<Stuff> dynamicDatabaseStuff = new DataBase<>();
                        Stuff stuff = dynamicDatabaseStuff.login(userNameString, passWordString);
                        ApplicationController.goTo("Stuff", stuff);
                    }
                }
            }
        }

    }

}
