package ku.cs.controller.user;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.ApplicationController;
import ku.cs.State;
import ku.cs.controller.components.NavbarUser;
import ku.cs.models.user.User;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class CreatePostController {
    @FXML
    private Label displayName;

    @FXML
    private Circle imageAccountCircle;

    @FXML
    private GridPane minisetting;
    @FXML
    private GridPane root;
    private User user;

    @FXML
    private Label roleDisplay;

    @FXML
    public void initialize() throws IOException {


        //initial style
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        String styleTheme = "/ku/cs/style/" + preferences.get("theme", null) + ".css";
        System.out.println(styleTheme);
        String icon = "/ku/cs/style/icon.css";
        String style = "/ku/cs/style/style.css";
        root.getStylesheets().add(getClass().getResource(styleTheme).toExternalForm());
        root.getStylesheets().add(getClass().getResource(icon).toExternalForm());
        root.getStylesheets().add(getClass().getResource(style).toExternalForm());

        //getObject from router
        user = (User) ApplicationController.getData();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/navBarUser.fxml"));
        GridPane navbar = (GridPane) fxmlLoader.load();
        NavbarUser navbarUser = fxmlLoader.getController();
        navbarUser.setUser(user);
        root.add(navbar, 0, 0);

        //set label
        displayName.setText(user.getUserName());
        roleDisplay.setText(user.getRole());

        //set image
        File desDir = new File("image" + System.getProperty("file.separator") + "accounts" + System.getProperty("file.separator") + user.getPathPicture());
        Image image = new Image(String.valueOf(desDir.toURI()), 500, 0, true, true);

        if (!image.isError()) {
            imageAccountCircle.setFill(new ImagePattern(image));
            imageAccountCircle.setStroke(Color.TRANSPARENT);
        }

    }
    @FXML
    void addPostButton(ActionEvent event) {

    }

    @FXML
    void handleAdminSettingButton(MouseEvent event) {

    }
}

