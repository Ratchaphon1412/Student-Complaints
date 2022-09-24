package ku.cs.controller.admin;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ku.cs.controller.components.AdminUserBanListController;
import ku.cs.models.user.User;
import ku.cs.models.user.UserList;
import ku.cs.service.DataBase;
import ku.cs.service.ProcessData;

import java.io.IOException;


public class AdminUserBanedListController {
    @FXML
    public GridPane gridPaneList;
    @FXML
    private GridPane adminpage;
    @FXML
    private GridPane listPostReportGrid;

    @FXML
    private PieChart countBanned;

    private ProcessData processData;
    private UserList userList;

    @FXML
    public void initialize() throws IOException {
        processData = new ProcessData<>();
        userList = new UserList(processData.getDataBase().getAccountList(),processData.getDataBase().getUserBanList());
        FXMLLoader fxmlLoader;
        //load NavBar
        fxmlLoader = new FXMLLoader();
        GridPane navbar = (GridPane) fxmlLoader.load(getClass().getResource("/ku/cs/components/navBarAdmin.fxml"));
        adminpage.add(navbar,0,0);



        int count = 0;
        for(User userBan : userList.getUserBanList()){

            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/listViewUserBanList.fxml"));
            AnchorPane listUser = (AnchorPane) fxmlLoader.load();
            AdminUserBanListController adminUserBanListController = fxmlLoader.getController();
            adminUserBanListController.setData(userBan);

            gridPaneList.add(listUser,0,count+1);
            GridPane.setMargin(listUser, new Insets(0,0,5,0));
            count++;
        }
        for(int num = 0 ; num < 5 ; num++){
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/banUserPostReport.fxml"));
            GridPane banPostUser = (GridPane) fxmlLoader.load();
            listPostReportGrid.add(banPostUser,0,num+1);
            GridPane.setMargin(banPostUser, new Insets(0,0,5,0));
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Banned",count-1),
                new PieChart.Data("preple",100-count-1)
        );
        countBanned.setClockwise(true);
        countBanned.setLabelsVisible(true);
        countBanned.setLabelLineLength(50);
        countBanned.setStartAngle(180);
        countBanned.setData(pieChartData);








    }



}
