package ku.cs.controller.admin;

import com.github.saacsos.FXRouter;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.controller.components.AdminUserBanListController;
import ku.cs.controller.components.BanUserReportController;
import ku.cs.models.admin.Admin;
import ku.cs.models.user.User;
import ku.cs.models.user.UserList;
import ku.cs.service.ProcessData;

import java.io.File;
import java.io.IOException;


public class AdminUserBanedListController {
    @FXML
    private GridPane adminpage;

    @FXML
    private PieChart countBanned;

    @FXML
    private PieChart countReportBan;

    @FXML
    private GridPane gridPaneList;

    @FXML
    private Circle imageAccount;

    @FXML
    private GridPane listPostReportGrid;


    @FXML
    private Label lordUserUnban1;

    @FXML
    private Label lordUserUnban2;

    @FXML
    private Label roleAccountLabel;

    @FXML
    private Label userNameAccountLabel;


    private ProcessData processData;
    private UserList userList;
//    private UserList userReportToBan;
    private BanAndUnBan banAndUnBan;
    private FXMLLoader fxmlLoader;
    private Admin account;

    @FXML
    public void initialize() throws IOException {
        account = (Admin) FXRouter.getData();
        roleAccountLabel.setText(account.getRole());
        userNameAccountLabel.setText(account.getUserName());

        //load NavBar
        fxmlLoader = new FXMLLoader();
        GridPane navbar = (GridPane) fxmlLoader.load(getClass().getResource("/ku/cs/components/navBarAdmin.fxml"));
        adminpage.add(navbar,0,0);

        refetch();
    }

    private void refetch() throws IOException {

        File desDir = new File("image"+System.getProperty("file.separator")+"accounts"+System.getProperty("file.separator")+account.getPathPicture());
        imageAccount.setFill(new ImagePattern(new Image(desDir.toURI().toString(),800, 0 ,true,true)));

        processData = new ProcessData<>();
        userList = processData.getUserList();

        listPostReportGrid.getChildren().clear();
        gridPaneList.getChildren().clear();
        banAndUnBan = new BanAndUnBan() {
            @Override
            public void onClickBanOrUnban() throws IOException {
                refetch();
            }
        };


        int count = 1;
        for(User userBan : userList.getUserBanList()){
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/listViewUserBanList.fxml"));
            AnchorPane listUser = (AnchorPane) fxmlLoader.load();
            AdminUserBanListController adminUserBanListController = fxmlLoader.getController();
            adminUserBanListController.setData(userBan,account,banAndUnBan);

            gridPaneList.add(listUser,0,count++);
            GridPane.setMargin(listUser, new Insets(0,0,5,0));
        }
        int num = 1;
        for(User userBan : userList.getUserRequestBan()){
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/banUserReport.fxml"));
            GridPane banPostUser = (GridPane) fxmlLoader.load();
            BanUserReportController banUserReportController = fxmlLoader.getController();
            banUserReportController.setData(userBan,account,banAndUnBan);

            listPostReportGrid.add(banPostUser,0,num++);
            GridPane.setMargin(banPostUser, new Insets(0,0,5,0));
        }




        ObservableList<PieChart.Data> pieChartBanned = FXCollections.observableArrayList(
                new PieChart.Data("All user Banned",userList.getUserBanList().size()),
                new PieChart.Data("All user",userList.getUserList().size())
        );
        int percentBanned = (userList.getUserBanList().size() / userList.getUserList().size());
        countBanned.setClockwise(true);
        countBanned.setLabelsVisible(true);
        countBanned.setLabelLineLength(50);
        countBanned.setStartAngle(180);
        countBanned.setData(pieChartBanned);

        ObservableList<PieChart.Data> pieChartReportBan = FXCollections.observableArrayList(
                new PieChart.Data("All user Banned",userList.getUserRequestBan().size()),
                new PieChart.Data("All user",userList.getUserList().size())
        );
        int percentReport = (userList.getUserRequestBan().size() / userList.getUserList().size());
        countReportBan.setClockwise(true);
        countReportBan.setLabelsVisible(true);
        countReportBan.setLabelLineLength(50);
        countReportBan.setStartAngle(180);
        countReportBan.setData(pieChartReportBan);
//        lordUserUnban1.setText(percentBanned);
//        lordUserUnban2.setText(percentReport);
        double a,b,c;
        a = userList.getUserBanList().size();
        b = userList.getUserRequestBan().size();
        c = userList.getUserList().size();

        lordUserUnban1.setText(String.valueOf(Math.ceil(a/c*100)));
        lordUserUnban2.setText(String.valueOf(Math.ceil(b/c*100)));
    }



}
