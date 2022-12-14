package ku.cs.controller.admin;
import animatefx.animation.*;
import animatefx.util.ParallelAnimationFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
import ku.cs.controller.components.ButtonThemeController;
import ku.cs.controller.components.admin.LogAccontController;
import ku.cs.controller.components.navbar.NavbarAdminController;
import ku.cs.models.admin.Admin;
import ku.cs.service.ProcessData;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.prefs.Preferences;

public class AdminController {
    @FXML
    private GridPane adminpage;
    @FXML
    private Label titleDashBoard;
    @FXML
    private Label titleRecent;
    @FXML
    private Label roleDisplay;
    @FXML
    private Label displayName;

    @FXML
    private Label titleReport;

    @FXML
    private Label titleAgency;

    @FXML
    private Label titleUser;

    @FXML
    private Label tablePicture;

    @FXML
    private Label tableUsername;

    @FXML
    private Label tableTime;
    @FXML
    private Label tableDate;


    @FXML
    private Circle imageAccountCircle;


    @FXML
    private ScrollPane scroll;
    @FXML
    private BarChart<String,Double> chart;

    @FXML
    private GridPane minisetting;

    private SwitchTheme changeTheme;

    private Admin account;
    private ProcessData processData;



    @FXML
    public void initialize() throws IOException{
        //initial style
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        String styleTheme = "/ku/cs/style/" +preferences.get("theme",null)+".css";
        System.out.println(styleTheme);
        String icon = "/ku/cs/style/icon.css";
        String style = "/ku/cs/style/style.css";
        adminpage.getStylesheets().add(getClass().getResource(styleTheme).toExternalForm());
        adminpage.getStylesheets().add(getClass().getResource(icon).toExternalForm());
        adminpage.getStylesheets().add(getClass().getResource(style).toExternalForm());
        //set Font
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        titleDashBoard.setFont(font);
        titleRecent.setFont(font);
        displayName.setFont(font);
        roleDisplay.setFont(font);
        titleReport.setFont(font);
        titleAgency.setFont(font);
        titleUser.setFont(font);
        tablePicture.setFont(font);
        tableUsername.setFont(font);
        tableTime.setFont(font);
        tableDate.setFont(font);



        //getObject Admin from login
        account = (Admin) ApplicationController.getData();
        //connect to Database
        processData = new ProcessData();
        //call method loadInitialAdminDashboard
        loadInitialAdminDashboard();
    }


    private void loadInitialAdminDashboard() throws IOException {
        //set username
        displayName.setText(account.getUserName());
        roleDisplay.setText(account.getRole());
        //get picture from objectAdmin
        File desDir = new File("image"+System.getProperty("file.separator")+"accounts"+System.getProperty("file.separator")+account.getPathPicture());
        Image imageAccount = new Image(String.valueOf(desDir.toURI()),500,0,true,true);

       if (!imageAccount.isError()){
           imageAccountCircle.setFill(new ImagePattern(imageAccount));
           imageAccountCircle.setStroke(Color.TRANSPARENT);
       }
        //load NavBar
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/admin/navBarAdmin.fxml"));
        GridPane navbar = (GridPane) fxmlLoader.load();
        NavbarAdminController navbarAdminController = fxmlLoader.getController();
        navbarAdminController.setAdmin(account);


        adminpage.add(navbar,0,0);
        //implements interface callback
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

        //Log zone
        //Create Grid pane and add to scroll pane
        GridPane listLog = new GridPane();
        listLog.setMaxHeight(30);
        listLog.setHgap(1000);
        scroll.setFitToWidth(true);
        scroll.setContent(listLog);

        //data log from Database
        int count = 1;
        List<LinkedHashMap<String,String>>logList = processData.getDataBase().getLogList();
        for(int row = logList.size()-1 ; row >= 0 ; row--){
            if(count >= 15){
                break;
            }
            //load components
            if(logList.get(row) != null){
                FXMLLoader fxmlLoader2 = new FXMLLoader();
                fxmlLoader2.setLocation(getClass().getResource("/ku/cs/components/admin/logAccount.fxml"));
                //get AnchorPane form component and send data to another controller
                AnchorPane anchorPane = (AnchorPane) fxmlLoader2.load();
                LogAccontController logAccontController = fxmlLoader2.getController();
                logAccontController.setData(logList.get(row));
                listLog.add(anchorPane,0,count);
                listLog.setMargin(anchorPane, new Insets(0,0,5,0));
            }
            count++;
        }

        //bar Chart
        XYChart.Series<String,Double> series = new XYChart.Series<>();
        series.setName("total User");
        series.getData().add(new XYChart.Data<>("Admin",(double) processData.getAdminList().getAdminList().size()));
        series.getData().add(new XYChart.Data<>("Staff",(double)processData.getStaffList().getStaffList().size()));
        series.getData().add(new XYChart.Data<>("User",(double)processData.getUserList().getUserList().size()));
        chart.getData().add(series);
    }


    @FXML
    public void testButton(ActionEvent actionEvent) {
        System.out.println(account.getUserName() + " " + account.getRole());
    }

    @FXML
    public void handleAdminUserBanListButton(ActionEvent actionEvent) {
        try {
            ApplicationController.goTo("banUser",account);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @FXML
    public void handleAdminAgencyListButton(ActionEvent actionEvent) {
        try {
            ApplicationController.goTo("AdminAgency",account);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @FXML
    public void handleAdminSettingButton(MouseEvent mouseEvent) {
        try {
            ApplicationController.goTo("Setting",account);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    @FXML
    private void gotoCategory() throws IOException{
        ApplicationController.goTo("AdminCategory", account);
    }





}