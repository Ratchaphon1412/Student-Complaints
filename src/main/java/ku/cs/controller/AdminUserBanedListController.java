package ku.cs.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ku.cs.controller.components.AdminUserBanListController;
import ku.cs.models.user.User;
import ku.cs.models.user.UserList;
import ku.cs.service.DataBase;

import java.io.IOException;


public class AdminUserBanedListController {
    public GridPane gridPaneList;
    private DataBase<User> dataBase;
    private UserList userList;
    @FXML
    public void initialize() throws IOException {

        dataBase = new DataBase<>();
        userList = dataBase.getListUserBaned();



        for(int i = 0; i < userList.getUserBanList().size();i++){

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/listViewUserBanList.fxml"));
            AnchorPane listUser = (AnchorPane) fxmlLoader.load();
            AdminUserBanListController adminUserBanListController = fxmlLoader.getController();
            adminUserBanListController.setData(userList.getUserBanList().get(i));

            gridPaneList.add(listUser,0,i+1);
            GridPane.setMargin(listUser, new Insets(0,0,5,0));
        }
    }



}
