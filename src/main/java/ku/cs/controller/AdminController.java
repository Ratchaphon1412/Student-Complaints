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
import ku.cs.models.admin.Admin;
import ku.cs.service.DataBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AdminController {
    @FXML
    private GridPane adminpage;
    @FXML
    private ListView<LinkedHashMap<String,String>> logListView;

    @FXML
    private Circle imageAccountCircle;
    private Admin account;
    private DataBase<Admin> dataBase;


    @FXML
    private ImageView img;

    @FXML
    private Label nameLabel;

    @FXML
    private Label roleLabel;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private List<LinkedHashMap<String,String>> logList;




    @FXML
    public void initialize() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        GridPane navbar =(GridPane) fxmlLoader.load(getClass().getResource("/ku/cs/components/navBarAdmin.fxml"));
        adminpage.add(navbar,0,0);
        account = (Admin) FXRouter.getData();
//        accountImage.setImage(new Image(getClass().getResource("/ku/cs/assets/images/download.png").toExternalForm()));
        Image imageAccount = new Image(getClass().getResource("/ku/cs/assets/images/114617.jpg").toExternalForm());
        imageAccountCircle.setFill(new ImagePattern(imageAccount));
        imageAccountCircle.setStroke(Color.TRANSPARENT);


        dataBase = new DataBase<>();


        this.logList = dataBase.getLogList();
        int colum = 0;

        for(int i = 0 ; i < logList.size()-1 ; i++){
            FXMLLoader fxmlLoader1 = new FXMLLoader();
            fxmlLoader1.setLocation(getClass().getResource("/ku/cs/components/logAccount.fxml"));

            AnchorPane anchorPane = (AnchorPane) fxmlLoader1.load();

            LogAccontController logAccontController = fxmlLoader1.getController();
            logAccontController.setData(logList.get(i));
            grid.add(anchorPane,colum,i+1);
            GridPane.setMargin(anchorPane, new Insets(0,0,5,0));

        }
    }

    @FXML
    public void testButton(ActionEvent actionEvent) {
        System.out.println(account.getUserName() + " " + account.getRole());
    }




}