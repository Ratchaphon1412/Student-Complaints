package ku.cs.controller.user;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.ApplicationController;
import ku.cs.State;
import ku.cs.controller.SwitchTheme;
import ku.cs.controller.components.ButtonThemeController;
import ku.cs.controller.components.NavbarUser;
import ku.cs.models.user.User;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class UserFeedController {


    @FXML
    private GridPane root;
    @FXML
    private GridPane feed;


    @FXML
    private Label userName;

    @FXML
    private Label roleUser;

    @FXML
    private Circle imageAccount;

    @FXML
    private GridPane minisetting;

    private SwitchTheme changeTheme;

    private User user;

    @FXML
    public void initialize() throws IOException {



        //initial style
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        String styleTheme = "/ku/cs/style/" +preferences.get("theme",null)+".css";
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
        root.add(navbar,0,0);

        //set label
        userName.setText(user.getUserName());
        roleUser.setText(user.getRole());

        //set image
        File desDir = new File("image"+System.getProperty("file.separator")+"accounts"+System.getProperty("file.separator")+user.getPathPicture());
        Image image = new Image(String.valueOf(desDir.toURI()),500,0,true,true);

        if (!image.isError()){
            imageAccount.setFill(new ImagePattern(image));
            imageAccount.setStroke(Color.TRANSPARENT);
        }
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
                root.getStylesheets().clear();
                String styleTheme = "/ku/cs/style/" +preferences.get("theme",null)+".css";
                String icon = "/ku/cs/style/icon.css";
                String style = "/ku/cs/style/style.css";
                root.getStylesheets().add(getClass().getResource(styleTheme).toExternalForm());
                root.getStylesheets().add(getClass().getResource(icon).toExternalForm());
                root.getStylesheets().add(getClass().getResource(style).toExternalForm());
            }
        };

        //Switch Theme
        FXMLLoader fxmlLoader1 = new FXMLLoader();
        fxmlLoader1.setLocation(getClass().getResource("/ku/cs/components/buttonTheme.fxml"));
        GridPane switchTheme = (GridPane)fxmlLoader1.load();
        ButtonThemeController buttonThemeController = fxmlLoader1.getController();
        buttonThemeController.setSwitchTheme(changeTheme);
        minisetting.add(switchTheme,1,1);


        for(int i = 1 ; i < 5 ; i++) {
            FXMLLoader fxmlLoaderFeed = new FXMLLoader();
            fxmlLoaderFeed.setLocation(getClass().getResource("/ku/cs/components/userFeed.fxml"));
            GridPane feedComponant = (GridPane) fxmlLoaderFeed.load();
            feed.add(feedComponant, 0, i);
            GridPane.setMargin(feedComponant, new Insets(0, 0, 15, 0));
        }
    }
}