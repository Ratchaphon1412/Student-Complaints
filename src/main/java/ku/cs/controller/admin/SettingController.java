package ku.cs.controller.admin;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import ku.cs.ApplicationController;
import ku.cs.controller.components.NavbarAdminController;
import ku.cs.models.admin.Admin;
import ku.cs.service.DynamicDatabase;

import ku.cs.service.ProcessData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SettingController {
    @FXML
    private Label username;
    @FXML
    private Label password;
    @FXML private Label role;
    @FXML
    private Circle bigImageaccountCircle;

    @FXML private Label miniuser;
    @FXML private Label minirole;
    @FXML
    private GridPane gridPane;
    @FXML private Circle imageaccountCircle;
    @FXML private ChoiceBox dropDown;
    private File file;
    private String path;
    private List<String> listfile;
    //private DataBase dataBase = new DataBase();
    private ProcessData dataBase = new ProcessData<>();



    private Admin account;

    public void initialize() throws IOException {

        account = (Admin) ApplicationController.getData();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/navBarAdmin.fxml"));
        GridPane navbar = (GridPane) fxmlLoader.load();
        NavbarAdminController navbarAdminController = fxmlLoader.getController();
        navbarAdminController.setAdmin(account);
        gridPane.add(navbar, 0, 0);
        username.setText(account.getUserName());
        password.setText(account.getPassWord());
        role.setText(account.getRole());
        miniuser.setText(account.getUserName());
        minirole.setText(account.getRole());

        File desDir = new File("image"+System.getProperty("file.separator")+"accounts"+System.getProperty("file.separator")+account.getPathPicture());
        Image imageAccount = new Image(desDir.toURI().toString());
        imageaccountCircle.setFill(new ImagePattern(imageAccount));
        imageaccountCircle.setStroke(Color.TRANSPARENT);

        // String url = getClass().getResource("image"+System.getProperty("file.separator")+"accounts"+System.getProperty("file.separator")+account.getPathPicture()).toExternalForm();
        bigImageaccountCircle.setFill(new ImagePattern(imageAccount));
        bigImageaccountCircle.setStroke(Color.TRANSPARENT);

        String font[] ={"Cloud-Bold", "FC-Sound"};
        dropDown.getItems().addAll(font);

    }

    @FXML
    public void fileChooser(ActionEvent actionEvent) {
        FileChooser choosefile = new FileChooser();
        listfile = new ArrayList<>();
        listfile.add("*.jpg");
        listfile.add("*.png");
        listfile.add("*jpeg");
        choosefile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Picture", listfile));

        file = choosefile.showOpenDialog(null);

        if (file != null) {
            path = file.getAbsolutePath();
            Image image = new Image(file.toURI().toString());
            bigImageaccountCircle.setFill(new ImagePattern(image));
        }
    }


    @FXML
    public void handleSaveSettingButton(ActionEvent actionEvent) throws IOException {

        //เดี๋ยวแก้
        dataBase.ChangPicture(account.getUserName(),account.getPassWord(), path, file);


        DynamicDatabase<Admin> database = new ProcessData<>();
        Admin admin = database.login(account.getUserName(),account.getPassWord());
        if(admin != null){
            //System.out.println("test");
            ApplicationController.goTo("Admin",admin);
        }else{
            System.out.println("error");

        }
    }



    @FXML
    public void handleCancleSettingButton(ActionEvent actionEvent) {
        try {
            ApplicationController.goTo("Admin");
        } catch (IOException e) {
            System.err.println(e);
        }
    }



}
