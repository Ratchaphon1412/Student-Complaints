package ku.cs.controller;

import com.github.saacsos.FXRouter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import ku.cs.models.admin.Admin;

import java.io.IOException;

public class AdminController {
    @FXML
    private GridPane adminpage;
    private Admin account;

    @FXML
    public void initialize() throws IOException {
        account = (Admin) FXRouter.getData();
        FXMLLoader fxmlLoader = new FXMLLoader();
        GridPane navbar =(GridPane) fxmlLoader.load(getClass().getResource("/ku/cs/components/navBarAdmin.fxml"));
        adminpage.add(navbar,0,0);
        System.out.println(account.getUserName());
    }



}