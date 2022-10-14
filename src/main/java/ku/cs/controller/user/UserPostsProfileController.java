package ku.cs.controller.user;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import ku.cs.State;
import ku.cs.controller.SwitchTheme;
import ku.cs.controller.components.ButtonThemeController;
import ku.cs.controller.components.ClickNamePostsUser;
import ku.cs.controller.components.NamePostsUserController;
import ku.cs.models.user.User;

import java.io.IOException;
import java.util.prefs.Preferences;

public class UserPostsProfileController {

    @FXML
    private GridPane myPostsGridPane;
    @FXML
    private Label showCallback;

    @FXML
    private GridPane userPage;
    @FXML
    private GridPane navbarGridPane;
    @FXML
    private GridPane minisetting;
    private FXMLLoader fxmlLoader;
    private User account;
    private SwitchTheme changeTheme;


    @FXML
    private void initialize() throws IOException {

        //initial style
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        String styleTheme = "/ku/cs/style/" +preferences.get("theme",null)+".css";
        System.out.println(styleTheme);
        String icon = "/ku/cs/style/icon.css";
        String style = "/ku/cs/style/style.css";
        userPage.getStylesheets().add(getClass().getResource(styleTheme).toExternalForm());
        userPage.getStylesheets().add(getClass().getResource(icon).toExternalForm());
        userPage.getStylesheets().add(getClass().getResource(style).toExternalForm());



        account = null;

        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/admin/navBarAdmin.fxml"));
        GridPane navbar = (GridPane) fxmlLoader.load();
        navbarGridPane.add(navbar,0,0);

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
                userPage.getStylesheets().clear();
                String styleTheme = "/ku/cs/style/" +preferences.get("theme",null)+".css";
                String icon = "/ku/cs/style/icon.css";
                String style = "/ku/cs/style/style.css";
                userPage.getStylesheets().add(getClass().getResource(styleTheme).toExternalForm());
                userPage.getStylesheets().add(getClass().getResource(icon).toExternalForm());
                userPage.getStylesheets().add(getClass().getResource(style).toExternalForm());
            }
        };

        //Switch Theme
        FXMLLoader fxmlLoader1 = new FXMLLoader();
        fxmlLoader1.setLocation(getClass().getResource("/ku/cs/components/buttonTheme.fxml"));
        GridPane switchTheme = (GridPane)fxmlLoader1.load();
        ButtonThemeController buttonThemeController = fxmlLoader1.getController();
        buttonThemeController.setSwitchTheme(changeTheme);
        minisetting.add(switchTheme,1,1);


        ClickNamePostsUser clickNamePostsUser = new ClickNamePostsUser() {
            @Override
            public void clickNamePostUser(String str) {
                showCallback.setText(str);
            }
        };


        for (int i = 0 ; i < 10 ; i++){
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/user/namePostsUserSmallWindow.fxml"));
            GridPane namePostsLoad = (GridPane) fxmlLoader.load();
            NamePostsUserController namePosts = fxmlLoader.getController();
            namePosts.setData(String.valueOf(i+". User"),clickNamePostsUser);
            myPostsGridPane.add(namePostsLoad,1,i+1);
            GridPane.setMargin(namePostsLoad, new Insets(0,0,5,0));


        }
    }


}
