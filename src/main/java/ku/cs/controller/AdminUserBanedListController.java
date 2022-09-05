package ku.cs.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;


public class AdminUserBanedListController {
    public GridPane gridPaneList;
    @FXML
    public void initialize() throws IOException {


        for(int i = 0; i < 10;i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/listViewUserBanList.fxml"));
            AnchorPane listUser = (AnchorPane) fxmlLoader.load();
            gridPaneList.add(listUser,0,i+1);
            GridPane.setMargin(listUser, new Insets(0,0,5,0));
        }
    }
}
