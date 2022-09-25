package ku.cs.controller.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class AdminAgencyController {
    @FXML
    private GridPane root;

    @FXML
    private GridPane staffList;
    private FXMLLoader fxmlLoader;


    @FXML
    public void initialize() throws IOException {
        fxmlLoader = new FXMLLoader();
        GridPane navbar = (GridPane) fxmlLoader.load(getClass().getResource("/ku/cs/components/navBarAdmin.fxml"));
        root.add(navbar,0,0);

        for(int i = 1 ; i < 5 ; i++) {
            fxmlLoader = new FXMLLoader();
            AnchorPane staffComponant = (AnchorPane) fxmlLoader.load(getClass().getResource("/ku/cs/components/staffList.fxml"));
            staffList.add(staffComponant, 0, i);
            GridPane.setMargin(staffComponant, new Insets(0,0,5,0));
        }
    }

    @FXML
    public void addAgencyButton(){

    }

    @FXML
    public void addStuffButton(){

    }
}
