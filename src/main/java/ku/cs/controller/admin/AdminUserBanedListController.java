package ku.cs.controller.admin;

import animatefx.animation.FadeIn;
import animatefx.animation.ZoomIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import ku.cs.ApplicationController;
import ku.cs.State;
import ku.cs.controller.SwitchTheme;
import ku.cs.controller.components.admin.AdminUserBanListController;
import ku.cs.controller.components.admin.BanUserReportController;
import ku.cs.controller.components.ButtonThemeController;
import ku.cs.controller.components.admin.DeleteUserReportController;
import ku.cs.controller.components.navbar.NavbarAdminController;
import ku.cs.models.admin.Admin;
import ku.cs.models.report.Report;
import ku.cs.models.user.User;
import ku.cs.models.user.UserList;
import ku.cs.service.ProcessData;

import java.io.File;

import java.io.IOException;
import java.util.List;
import java.util.prefs.Preferences;



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
    private GridPane listPostReportGrid;
    @FXML
    private Label countUserUnbanLabel;
    @FXML
    private Label countUserBanLabel;
    @FXML
    private Label userName;
    @FXML
    private Label roleDisplay;

    @FXML
    private Label lordUserUnban1;

    @FXML
    private Label lordUserUnban2;

    @FXML
    private Circle imageAccountCircle;

    @FXML
    private  GridPane minisetting;
    @FXML
    private Label ListofUserReportLabel;

    private ProcessData processData;
    private UserList userList;


    private Admin account;
    private SwitchTheme changeTheme;

    //    private UserList userReportToBan;

    private BanAndUnBan banAndUnBan;
    private FXMLLoader fxmlLoader;


    @FXML
    public void initialize() throws IOException {
        //initial style
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        String styleTheme = "/ku/cs/style/" +preferences.get("theme",null)+".css";
        System.out.println(styleTheme);
        String icon = "/ku/cs/style/icon.css";
        String style = "/ku/cs/style/style.css";
        adminpage.getStylesheets().add(getClass().getResource(styleTheme).toExternalForm());
        adminpage.getStylesheets().add(getClass().getResource(icon).toExternalForm());
        adminpage.getStylesheets().add(getClass().getResource(style).toExternalForm());
        //set font
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        ListofUserReportLabel.setFont(font);
        roleDisplay.setFont(font);
        userName.setFont(font);
        countUserBanLabel.setFont(font);
        countUserUnbanLabel.setFont(font);
        //set Animation
        new FadeIn(adminpage).setSpeed(0.8).play();
        //get object Admin
        account = (Admin) ApplicationController.getData();
        // connect to database
        processData = new ProcessData<>();
        userList = processData.getUserList();
//        userReportToBan = processData.getReportList().getReportToBanUser();
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


        //load NavBar
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/admin/navBarAdmin.fxml"));
        GridPane navbar = (GridPane) fxmlLoader.load();
        NavbarAdminController navbarAdminController = fxmlLoader.getController();
        navbarAdminController.setAdmin(account);
        adminpage.add(navbar,0,0);

        changeTheme = new SwitchTheme() {
            @Override
            public void changeTheme(String theme) throws IOException {
                //save theme
                State state = new State();
                state.setTempData();
                state.saveThemeToConfig(theme);
                //change state
                Preferences preferences = Preferences.userRoot().node(State.class.getName());
                preferences.put("theme",theme);
                //change stylesheet in main page
                adminpage.getStylesheets().clear();
                String styleTheme = "/ku/cs/style/" +preferences.get("theme",null)+".css";
                String icon = "/ku/cs/style/icon.css";
                String style = "/ku/cs/style/style.css";
                adminpage.getStylesheets().add(getClass().getResource(styleTheme).toExternalForm());
                adminpage.getStylesheets().add(getClass().getResource(icon).toExternalForm());
                adminpage.getStylesheets().add(getClass().getResource(style).toExternalForm());
            }
        };
        //Switch Theme
        FXMLLoader fxmlLoader1 = new FXMLLoader();
        fxmlLoader1.setLocation(getClass().getResource("/ku/cs/components/buttonTheme.fxml"));
        GridPane switchTheme = (GridPane)fxmlLoader1.load();
        ButtonThemeController buttonThemeController = fxmlLoader1.getController();
        buttonThemeController.setSwitchTheme(changeTheme);
        minisetting.add(switchTheme,1,1);



        refetch();
    }

    private void refetch() throws IOException {

//        File desDir = new File("image"+System.getProperty("file.separator")+"accounts"+System.getProperty("file.separator")+account.getPathPicture());
//        imageAccount.setFill(new ImagePattern(new Image(desDir.toURI().toString(),800, 0 ,true,true)));

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
        System.out.println(userList.getUserBanList());
        for(User userUnban : userList.getUserBanList()){
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/admin/listViewUserBanList.fxml"));
            AnchorPane listUser = (AnchorPane) fxmlLoader.load();
            AdminUserBanListController adminUserBanListController = fxmlLoader.getController();
            adminUserBanListController.setData(userUnban,account,banAndUnBan);

            gridPaneList.add(listUser,0,count++);
            GridPane.setMargin(listUser, new Insets(0,0,5,0));
        }
        int num = 1;
        for(User userBan : userList.getUserRequestBan()){
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/admin/banUserReport.fxml"));
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
//        int percentBanned = (userList.getUserBanList().size() / userList.getUserList().size());
        countBanned.setClockwise(true);
        countBanned.setLabelsVisible(true);
        countBanned.setLabelLineLength(50);
        countBanned.setStartAngle(180);
        countBanned.setData(pieChartBanned);

        ObservableList<PieChart.Data> pieChartReportBan = FXCollections.observableArrayList(
                new PieChart.Data("All user Banned",userList.getUserRequestBan().size()),
                new PieChart.Data("All user",userList.getUserList().size())
        );
//        int percentReport = (userList.getUserRequestBan().size() / userList.getUserList().size());
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
        System.out.println(a + " " + c);
        System.out.println(b+ " "+c);

        lordUserUnban1.setText(String.valueOf(Math.ceil(a/c*100)));
        lordUserUnban2.setText(String.valueOf(Math.ceil(b/c*100)));

    }
    @FXML
    public void handleAdminSettingButton(MouseEvent mouseEvent) {
        try {
            ApplicationController.goTo("Setting", account);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}





