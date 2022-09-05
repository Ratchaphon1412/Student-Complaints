package ku.cs.controller;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.controller.components.LogAccontController;
import ku.cs.models.admin.Admin;
import ku.cs.service.DataBase;

import java.io.IOException;
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
    private ImageView img;

    @FXML
    private Label nameLabel;

    @FXML
    private Label roleLabel;
    @FXML
    private ScrollPane scroll;

    private List<LinkedHashMap<String,String>> logList;

    private Admin account;
    private DataBase<Admin> dataBase;



    @FXML
    public void initialize() throws IOException{
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
//        Image imageAccount = new Image(account.getPathPicture().toExternalForm());
        //test
        Image imageAccount = new Image(getClass().getResource("/ku/cs/assets/images/114617.jpg").toExternalForm());
        //add picture to circle
        imageAccountCircle.setFill(new ImagePattern(imageAccount));
        imageAccountCircle.setStroke(Color.TRANSPARENT);
        //connect to Database
        dataBase = new DataBase<>();
        this.logList = dataBase.getLogList();
        //Log zone
        //Create Gridpane and add to scrollpane
        GridPane listLog = new GridPane();
         listLog.setStyle("-fx-background-color:#2D3440;");
        listLog.setMaxHeight(30);
        listLog.setHgap(1000);
        scroll.setFitToWidth(true);
        scroll.setContent(listLog);

        //loop log (get log from database) and show
        for(int row = 0 ; row < logList.size()-1 ; row++){
            //load components
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

    @FXML
    public void testButton(ActionEvent actionEvent) {
        System.out.println(account.getUserName() + " " + account.getRole());
    }




}