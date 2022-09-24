package ku.cs.controller.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.ApplicationController;
import ku.cs.controller.components.AdminUserBanListController;
import ku.cs.controller.components.BanUserReportController;
import ku.cs.controller.components.NavbarAdminController;
import ku.cs.models.admin.Admin;
import ku.cs.models.report.Report;
import ku.cs.models.user.User;
import ku.cs.models.user.UserList;
import ku.cs.service.ProcessData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class AdminUserBanedListController {
    @FXML
    public GridPane gridPaneList;
    @FXML
    private GridPane adminpage;
    @FXML
    private GridPane listPostReportGrid;

    @FXML
    private Label userName;
    @FXML
    private Label roleDisplay;
    @FXML
    private PieChart countBanned;

    @FXML
    private Circle imageAccountCircle;

    private ProcessData processData;
    private UserList userList;
    private ArrayList<Report> userReportToBan;
    private Admin account;

    @FXML
    public void initialize() throws IOException {
        //get object Admin
        account = (Admin) ApplicationController.getData();


        // connect to database
        processData = new ProcessData<>();
        userList = processData.getUserList();
        userReportToBan = processData.getReportList().getReportToBanUser();
        //call initialUserpage
        initialUserBanpage();


    }
    private void initialUserBanpage() throws IOException {
        userName.setText(account.getUserName());
        roleDisplay.setText(account.getRole());
        //get picture from objectAdmin
        File desDir = new File("image"+System.getProperty("file.separator")+"accounts"+System.getProperty("file.separator")+account.getPathPicture());
        Image imageAccount = new Image(desDir.toURI().toString());
        imageAccountCircle.setFill(new ImagePattern(imageAccount));
        imageAccountCircle.setStroke(Color.TRANSPARENT);


        FXMLLoader fxmlLoader;
        //load NavBar
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/navBarAdmin.fxml"));
        GridPane navbar = (GridPane) fxmlLoader.load();
        NavbarAdminController navbarAdminController = fxmlLoader.getController();
        navbarAdminController.setAdmin(account);
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
        int num = 1;
        for(Report tem : userReportToBan){
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/banUserPostReport.fxml"));
            GridPane banPostUser = (GridPane) fxmlLoader.load();
            BanUserReportController banUserReportController = fxmlLoader.getController();
            banUserReportController.setData(tem);

            listPostReportGrid.add(banPostUser,0,num++);
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
//        countBanned.getData().clear();
    }






}
