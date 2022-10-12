package ku.cs.controller.components.navbar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import ku.cs.ApplicationController;
import ku.cs.models.staff.Staff;
import ku.cs.models.user.User;

import java.io.IOException;

public class NavbarStaffController {

    private Staff staff;
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
    private void logOut() throws IOException {
        ApplicationController.goTo("Login");
    }
    @FXML
    private void goDashBoardStaff() throws IOException {
        ApplicationController.goTo("Staff",staff);
    }
    @FXML
    void gotoSetting(ActionEvent event) {
        try {
            ApplicationController.goTo("SettingStaff",staff);
        } catch (IOException e) {
            System.err.println(e);
        }
    }




    public void setStaff(Staff staff){
        this.staff = staff;
    }


}
