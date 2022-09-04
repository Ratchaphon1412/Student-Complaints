package ku.cs.controller;

import com.github.saacsos.FXRouter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import ku.cs.models.admin.Admin;

import java.io.IOException;

public class NavbarAdminController {

    @FXML
    private GridPane navBar;
    private Admin account;
    @FXML
    public void initialize() throws IOException {
        account = (Admin) FXRouter.getData();
        FXMLLoader fxmlLoader = new FXMLLoader();
        HBox buttonWindows = (HBox)fxmlLoader.load(getClass().getResource("/ku/cs/components/buttonWindows.fxml"));
        buttonWindows.setPrefWidth(50);
        buttonWindows.setPrefHeight(18);
        buttonWindows.setAlignment(Pos.CENTER);
        navBar.add(buttonWindows,0,0);
        navBar.setMargin(buttonWindows,new Insets(55, 70, 70, 0));
    }

}
