package ku.cs.controller;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.ApplicationController;
import ku.cs.models.admin.Admin;

import java.io.File;
import java.io.IOException;


public class SettingController {
    @FXML
    private Label username;
    @FXML
    private Label password;
    @FXML private Label role;
    @FXML
    private ImageView image;

    @FXML private Label miniuser;
    @FXML private Label minirole;
    @FXML
    private GridPane gridPane;
    @FXML private Circle imageaccountCircle;


    private Admin account;

    public void initialize() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        GridPane navbar = (GridPane) fxmlLoader.load(getClass().getResource("/ku/cs/components/navBarAdmin.fxml"));
        gridPane.add(navbar, 0, 0);

        account =  (Admin) FXRouter.getData();
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
        image.setImage(imageAccount);


    }
    @FXML
    public void handleCancleSettingButton(ActionEvent actionEvent) {
        try {
            ApplicationController.goTo("Admin");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

//    @FXML
//    public void handleChoosePictureButton(ActionEvent actionEvent) {
//        try {
//            ApplicationController.goTo("Admin");
//        } catch (IOException e) {
//            System.err.println(e);
//        }
//    }
}
