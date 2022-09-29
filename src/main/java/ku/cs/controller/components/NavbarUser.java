package ku.cs.controller.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import ku.cs.ApplicationController;
import ku.cs.models.admin.Admin;
import ku.cs.models.user.User;

import java.io.IOException;

public class NavbarUser {
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
//        ApplicationController.goTo("Admin",user);
    }
    @FXML
    private void gotoAgency() throws IOException {
//        ApplicationController.goTo("AdminAgency",user);
    }

    @FXML
    private void gotoUserBan() throws IOException {
//        ApplicationController.goTo("banUser",user);
    }

    @FXML
    private void gotoSetting() throws IOException {
//        ApplicationController.goTo("Setting",user);
    }

    @FXML
    private void logOut() throws IOException {
        ApplicationController.goTo("Login");
    }
    public void setUser(User user){
        this.user = user;
    }

}
