package ku.cs.controller.components.navbar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import ku.cs.ApplicationController;
import ku.cs.models.user.User;

import java.io.IOException;

public class NavbarUserController {
    private User user;

    @FXML
    private GridPane navBar;

    @FXML
    public void initialize() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        HBox buttonWindows = (HBox)fxmlLoader.load(getClass().getResource("/ku/cs/components/buttonWindows.fxml"));
        buttonWindows.setPrefWidth(50);
        buttonWindows.setPrefHeight(18);
        buttonWindows.setAlignment(Pos.CENTER);
        navBar.add(buttonWindows,0,0);
        navBar.setMargin(buttonWindows,new Insets(60, 70, 70, 3));
    }


    @FXML
    private void goDashBoard() throws IOException {
        ApplicationController.goTo("User",user);
    }
    @FXML
    private void gotoCreatePost() throws IOException {
        ApplicationController.goTo("Post",user);
    }

    @FXML
    private void gotoSetting() throws IOException {
       ApplicationController.goTo("UserSetting",user);
    }

    @FXML
    private void logOut() throws IOException {
        ApplicationController.goTo("Login");
    }
    public void setUser(User user){
        this.user = user;
    }

}

