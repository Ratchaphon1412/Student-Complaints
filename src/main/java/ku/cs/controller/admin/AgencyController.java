package ku.cs.controller.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ku.cs.ApplicationController;
import ku.cs.controller.components.NavbarAdminController;
import ku.cs.models.admin.Admin;

import java.io.IOException;

public class AgencyController {
    @FXML
    private GridPane root;

    @FXML
    private GridPane staffList;

    private Admin account;


    @FXML
    public void initialize() throws IOException {
        account = (Admin) ApplicationController.getData();
        //load navbar
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/navBarAdmin.fxml"));
        GridPane navbar = (GridPane) fxmlLoader.load();
        NavbarAdminController navbarAdminController = fxmlLoader.getController();
        navbarAdminController.setAdmin(account);
        root.add(navbar,0,0);
        //load stuff list
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/staffList.fxml"));
        AnchorPane staffComponant = (AnchorPane) fxmlLoader.load();
        staffList.add(staffComponant,0,0);
    }

    @FXML
    public void addAgencyButton(){

    }

    @FXML
    public void addStuffButton(){

    }
}
