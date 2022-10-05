package ku.cs.controller.admin;

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
import javafx.scene.text.Font;
import ku.cs.ApplicationController;
import ku.cs.State;
import ku.cs.controller.SwitchTheme;
import ku.cs.controller.components.ButtonThemeController;
import ku.cs.controller.components.navbar.NavbarAdminController;
import ku.cs.models.admin.Admin;


import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class AdminCategoryController {
    @FXML
    private Label displayName;
    @FXML
    private Label roleDisplay;
    @FXML
    private Label tableTitleAgency;

    @FXML
    private Label tableTitleRole;

    @FXML
    private Label tableTitleUserName;

    @FXML
    private Label titleAgency;

    @FXML
    private Label titleReport;

    @FXML
    private Label titleReportAgency;

    @FXML
    private Label titleStuffList;

    @FXML
    private Circle imageAccountCircle;
    private Admin account;
    private SwitchTheme changeTheme;
    @FXML
    private GridPane minisetting;
    @FXML
    private GridPane root;
    private FXMLLoader fxmlLoader;
    @FXML
    public void initialize() throws IOException{
        //initial style
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        String styleTheme = "/ku/cs/style/" +preferences.get("theme",null)+".css";
        System.out.println(styleTheme);
        String icon = "/ku/cs/style/icon.css";
        String style = "/ku/cs/style/style.css";
        root.getStylesheets().add(getClass().getResource(styleTheme).toExternalForm());
        root.getStylesheets().add(getClass().getResource(icon).toExternalForm());
        root.getStylesheets().add(getClass().getResource(style).toExternalForm());
        //set Font
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        titleStuffList.setFont(font);
        titleReport.setFont(font);
        tableTitleAgency.setFont(font);
        titleReportAgency.setFont(font);
        titleAgency.setFont(font);
        tableTitleAgency.setFont(font);
        tableTitleRole.setFont(font);
        tableTitleUserName.setFont(font);
        displayName.setFont(font);
        roleDisplay.setFont(font);

        account = (Admin)ApplicationController.getData();
        displayName.setText(account.getUserName());
        roleDisplay.setText(account.getRole());


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
        initializeAdminCategory();
    }
    private void initializeAdminCategory() throws IOException{
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

        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/navBarAdmin.fxml"));
        GridPane navbar = (GridPane) fxmlLoader.load();
        NavbarAdminController navbarAdminController = fxmlLoader.getController();
        navbarAdminController.setAdmin(account);
        root.add(navbar,0,0);
    }
    @FXML
    void goToAddCategoryButton(ActionEvent event) throws IOException {
        ApplicationController.goTo("addCategory");
    }

    @FXML
    public void handleAdminSettingButton(MouseEvent mouseEvent) {
        try {
            ApplicationController.goTo("Setting",account);
        } catch (IOException e) {
            System.err.println(e);
        }
    }


}
