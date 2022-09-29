package ku.cs.controller.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.ApplicationController;
import ku.cs.controller.components.NavbarAdminController;
import ku.cs.models.admin.Admin;

import java.io.File;
import java.io.IOException;

public class AgencyController {
    @FXML
    private GridPane root;

    @FXML
    private GridPane staffListGridPane;

    @FXML
    private Circle imageAccountCircle;
    @FXML
    private Label displayName;
    @FXML
    private Label roleDisplay;


    private Admin account;





    @FXML
    public void initialize() throws IOException {
        account = (Admin) ApplicationController.getData();


        //load navbar
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/navBarAdmin.fxml"));
        GridPane navbar = (GridPane) fxmlLoader.load();
        //send object to nav
        NavbarAdminController navbarAdminController = fxmlLoader.getController();
        navbarAdminController.setAdmin(account);

        root.add(navbar,0,0);
        //load stuff list
        FXMLLoader fxmlLoader1 = new FXMLLoader();
        fxmlLoader1.setLocation(getClass().getResource("/ku/cs/components/staffList.fxml"));
        AnchorPane staffComponant = (AnchorPane) fxmlLoader1.load();
        staffListGridPane.add(staffComponant,0,0);
        //set display
        displayName.setText(account.getUserName());
        roleDisplay.setText(account.getRole());

        File desDir = new File("image"+System.getProperty("file.separator")+"accounts"+System.getProperty("file.separator")+account.getPathPicture());
        Image imageAccount = new Image(String.valueOf(desDir.toURI()),500,0,true,true);

        if (!imageAccount.isError()){
            imageAccountCircle.setFill(new ImagePattern(imageAccount));
            imageAccountCircle.setStroke(Color.TRANSPARENT);
        }


    }

    @FXML
    public void addAgencyButton(){

    }

    @FXML
    public void addStuffButton() throws IOException {
        ApplicationController.goToNew("RegisterStuff");
    }
}
