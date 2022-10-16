package ku.cs.controller.admin;

import animatefx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import ku.cs.ApplicationController;

import ku.cs.State;
import ku.cs.controller.SwitchFonts;
import ku.cs.controller.SwitchTheme;
import ku.cs.controller.components.ButtonThemeController;

import ku.cs.controller.components.navbar.NavbarAdminController;

import ku.cs.models.admin.Admin;
import ku.cs.service.DynamicDatabase;

import ku.cs.service.ProcessData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.prefs.Preferences;


public class SettingController<DataObject> {
    @FXML
    private Label username;
    @FXML
    private Label password;
    @FXML private Label role;

    @FXML
    private  Label titleSetting;

    @FXML
    private Label titleUserName;

    @FXML
    private Label titlePassWord;

    @FXML
    private Label titleRole;
    @FXML
    private Label titleTheme;
    @FXML
    private Label titleFont;


    @FXML private Label miniuser;
    @FXML private Label minirole;
    @FXML
    private Circle bigImageaccountCircle;
    @FXML
    private GridPane gridPane;
    @FXML private Circle imageaccountCircle;
    @FXML private ChoiceBox<String> dropDown;
    @FXML private GridPane miniGridePane;

    private File file;
    private String path;
    private List<String> listfile;

    private ProcessData dataBase = new ProcessData<>();
    private SwitchTheme changeTheme;
    private SwitchFonts changeFonts;



    private Admin account;

    public void initialize() throws IOException {
        //initial style
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        String styleTheme = "/ku/cs/style/" +preferences.get("theme",null)+".css";
        System.out.println(styleTheme);
        String icon = "/ku/cs/style/icon.css";
        String style = "/ku/cs/style/style.css";
        gridPane.getStylesheets().add(getClass().getResource(styleTheme).toExternalForm());
        gridPane.getStylesheets().add(getClass().getResource(icon).toExternalForm());
        gridPane.getStylesheets().add(getClass().getResource(style).toExternalForm());

        //set Font
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),18);
        username.setFont(font);
        password.setFont(font);
        role.setFont(font);
        titleSetting.setFont(font);
        titleSetting.setWrapText(true);
        titleUserName.setFont(font);
        titlePassWord.setFont(font);
        titleRole.setFont(font);
        titleTheme.setFont(font);
        titleFont.setFont(font);
        titleFont.setWrapText(true);
        miniuser.setFont(font);
        miniuser.setWrapText(true);
        minirole.setFont(font);


        //get object Admin
        System.out.println(ApplicationController.getData().getClass().getName());
        account = (Admin) ApplicationController.getData();

        initializeSetting();

    }


    private void initializeSetting() throws IOException {
        //load nav
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/admin/navBarAdmin.fxml"));
        GridPane navbar = (GridPane) fxmlLoader.load();
        NavbarAdminController navbarAdminController = fxmlLoader.getController();
        navbarAdminController.setAdmin(account);
        gridPane.add(navbar, 0, 0);

        //set label
        username.setText(account.getUserName());
        password.setText(account.getPassWord());
        role.setText(account.getRole());
        miniuser.setText(account.getUserName());
        minirole.setText(account.getRole());

        //set image account
        File desDir = new File("image"+System.getProperty("file.separator")+"accounts"+System.getProperty("file.separator")+account.getPathPicture());
        Image imageAccount = new Image(desDir.toURI().toString());
        imageaccountCircle.setFill(new ImagePattern(imageAccount));
        imageaccountCircle.setStroke(Color.TRANSPARENT);
        bigImageaccountCircle.setFill(new ImagePattern(imageAccount));
        bigImageaccountCircle.setStroke(Color.TRANSPARENT);

        //font choice
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        String[] font ={"Cloud-Bold", "FC-Sound","pixelletMedium"};
        dropDown.getItems().addAll(font);
        dropDown.setValue(preferences.get("font", null));


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
                gridPane.getStylesheets().clear();
                String styleTheme = "/ku/cs/style/" +preferences.get("theme",null)+".css";
                String icon = "/ku/cs/style/icon.css";
                String style = "/ku/cs/style/style.css";
                gridPane.getStylesheets().add(getClass().getResource(styleTheme).toExternalForm());
                gridPane.getStylesheets().add(getClass().getResource(icon).toExternalForm());
                gridPane.getStylesheets().add(getClass().getResource(style).toExternalForm());

            }
        };

//        theme
        FXMLLoader fxmlLoader1 = new FXMLLoader();
        fxmlLoader1.setLocation(getClass().getResource("/ku/cs/components/buttonTheme.fxml"));
        GridPane switchTheme = (GridPane)fxmlLoader1.load();
        ButtonThemeController buttonThemeController = fxmlLoader1.getController();
        buttonThemeController.setSwitchTheme(changeTheme);
        miniGridePane.add(switchTheme,1,1);

        //set Font
        changeFonts = new SwitchFonts() {
            @Override
            public void changeFonts(String fonts) throws IOException {
                //list
                HashMap<String,String> listFonts = new HashMap<>();
                listFonts.put("Cloud-Bold","Cloud-Bold.otf");
                listFonts.put("FC-Sound","FC-Sound.otf");
                listFonts.put("pixelletMedium","pixelletMedium.ttf");
                listFonts.put("Kanit","Kanit-Regular.ttf");
                //save theme
                State state = new State();
                state.setTempData();
                state.saveFontToConfig(listFonts.get(fonts));
                //change state
                Preferences preferences = Preferences.userRoot().node(State.class.getName());
                preferences.put("font",listFonts.get(fonts));
            }
        };





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
        if(file != null){
            dataBase.changePicture(account.getEmail(),account.getPassWord(), path, file);
            ProcessData<Admin> database = new ProcessData<>();
            account = database.getAdminList().getAdmin(account.getEmail());
        }
        //change fonts
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        if(!dropDown.getValue().equals(preferences.get("font",null))){
            changeFonts.changeFonts(dropDown.getValue().toString());
        }
        ApplicationController.goTo("Admin",account);

    }

    @FXML
    void handleChangePasswordButton(ActionEvent event) {
        try {
            ApplicationController.goToNew("changePassword");
        } catch (IOException e) {
            System.err.println(e);
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
