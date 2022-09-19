package ku.cs.controller;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.ApplicationController;
import ku.cs.controller.components.LogAccontController;
import ku.cs.models.admin.Admin;
import ku.cs.service.DataBase;
import ku.cs.service.ProcessData;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;

public class AdminController {
    @FXML
    private GridPane adminpage;
    @FXML
    private ListView<LinkedHashMap<String,String>> logListView;
    @FXML
    private Label displatName;
    @FXML
    private Label roleDisplay;

    @FXML
    private Circle imageAccountCircle;

    @FXML
    private  NumberAxis yLogin = new NumberAxis();

    @FXML
    private CategoryAxis xMonth = new CategoryAxis();

    @FXML
    private Label roleLabel;
    @FXML
    private ScrollPane scroll;
    @FXML
    private BarChart<String,Double> chart;

    @FXML
    private GridPane minisetting;

    private SwitchTheme changeTheme;

    private List<LinkedHashMap<String,String>> logList;

    private Admin account;
    private ProcessData processData;



    @FXML
    public void initialize() throws IOException, URISyntaxException {
        //load NavBar
        FXMLLoader fxmlLoader = new FXMLLoader();
        GridPane navbar = (GridPane) fxmlLoader.load(getClass().getResource("/ku/cs/components/navBarAdmin.fxml"));
        //addMainpage
        adminpage.add(navbar,0,0);
        //getObject Admin from login
        account = (Admin) FXRouter.getData();
        displatName.setText(account.getUserName());
        roleDisplay.setText(account.getRole());
        //get picture from objectAdmin
       File desDir = new File("image"+System.getProperty("file.separator")+"accounts"+System.getProperty("file.separator")+account.getPathPicture());
       Image imageAccount = new Image(desDir.toURI().toString());
       //Switch Theme
       GridPane switchTheme = (GridPane)fxmlLoader.load(getClass().getResource("/ku/cs/components/buttonTheme.fxml"));
        minisetting.add(switchTheme,1,1);
        //implements interface callback
        changeTheme = new SwitchTheme() {
            @Override
            public void changeTheme(String theme) {

            }
        };

//
       imageAccountCircle.setFill(new ImagePattern(imageAccount));
        imageAccountCircle.setStroke(Color.TRANSPARENT);
        //connect to Database
        processData = new ProcessData();
        this.logList = processData.getDataBase().getLogList();

        //Log zone
        //Create Gridpane and add to scrollpane
        GridPane listLog = new GridPane();
//         listLog.setStyle("-fx-background-color:#2D3440;");
        listLog.setMaxHeight(30);
        listLog.setHgap(1000);
        scroll.setFitToWidth(true);
        scroll.setContent(listLog);

        //loop log (get log from database) and show
        for(int row = 0 ; row < logList.size()-1 ; row++){
            //load components


           if(logList.get(row) != null){
               FXMLLoader fxmlLoader1 = new FXMLLoader();
               fxmlLoader1.setLocation(getClass().getResource("/ku/cs/components/logAccount.fxml"));
               //get AnchorPane form component and send data to another controller
               AnchorPane anchorPane = (AnchorPane) fxmlLoader1.load();
               LogAccontController logAccontController = fxmlLoader1.getController();
               logAccontController.setData(logList.get(row));
               listLog.add(anchorPane,0,row+1);
               listLog.setMargin(anchorPane, new Insets(0,0,5,0));

           }

        }
        XYChart.Series<String,Double> series = new XYChart.Series<>();
        series.setName("Past work 3 months");
        series.getData().add(new XYChart.Data("Jan",10.0));
        series.getData().add(new XYChart.Data("Feb",20.0));
        series.getData().add(new XYChart.Data("Mar",10.0));
        chart.getData().add(series);

    }

    @FXML
    public void testButton(ActionEvent actionEvent) {
        System.out.println(account.getUserName() + " " + account.getRole());
    }

    @FXML
    public void handleAdminUserBanListButton(ActionEvent actionEvent) {
        try {
            ApplicationController.goTo("AdminUserBanList");
        } catch (IOException e) {
            System.err.println(e);
        }
    }




}