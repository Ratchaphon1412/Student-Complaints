package ku.cs.controller.components;

import com.github.saacsos.FXRouter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import ku.cs.ApplicationController;
import ku.cs.models.admin.Admin;

import java.io.IOException;

public class NavbarAdminController {
    private Admin admin;

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
        ApplicationController.goTo("Admin",admin);
    }
   @FXML
    private void gotoAgency() throws IOException {
       ApplicationController.goTo("Agency",admin);
   }

   @FXML
    private void gotoUserBan() throws IOException {
        ApplicationController.goTo("banUser",admin);
   }

   @FXML
    private void gotoSetting() throws IOException {
        ApplicationController.goTo("Setting",admin);
   }

   public void setAdmin(Admin admin){
        this.admin = admin;
   }
}
