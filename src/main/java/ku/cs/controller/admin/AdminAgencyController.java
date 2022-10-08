package ku.cs.controller.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
import ku.cs.controller.components.staff.StaffListAgencyController;
import ku.cs.models.admin.Admin;
import ku.cs.models.staff.Staff;
import ku.cs.models.staff.StaffList;
import ku.cs.service.ProcessData;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class AdminAgencyController {
    @FXML
    private GridPane root;

    @FXML
    private GridPane staffListGridPane;
    @FXML
    private GridPane gridPaneAgency;

    @FXML
    private Label displayName;

    @FXML
    private Label roleDisplay;

    @FXML
    private Circle imageAccountCircle;

    @FXML
    private GridPane minisetting;

    @FXML
    private Label titleStuffList;

    @FXML
    private  Label titleAgency;

    @FXML
    private Label titleReport;

    @FXML
    private  Label titleReportAgency;

    @FXML
    private Label tableTitleUserName;

    @FXML
    private  Label tableTitleAgency;

    @FXML
    private  Label tableTitleRole;



    private SwitchTheme changeTheme;

    private FXMLLoader fxmlLoader;
    private ProcessData processData;
    private StaffList staffListData;

    private Admin account;

    private AgencyLoad agencyLoad;


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
        //set Font
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        displayName.setFont(font);
        roleDisplay.setFont(font);
        titleStuffList.setFont(font);
        titleAgency.setFont(font);
        titleReport.setFont(font);
        titleReportAgency.setFont(font);
        tableTitleUserName.setFont(font);
        tableTitleAgency.setFont(font);
        tableTitleRole.setFont(font);




       account = (Admin)ApplicationController.getData();
        displayName.setText(account.getUserName());
        roleDisplay.setText(account.getRole());

        //get picture from objectAdmin
        File desDir = new File("image"+System.getProperty("file.separator")+"accounts"+System.getProperty("file.separator")+account.getPathPicture());
        Image imageAccount = new Image(String.valueOf(desDir.toURI()),500,0,true,true);

        if (!imageAccount.isError()){
            imageAccountCircle.setFill(new ImagePattern(imageAccount));
            imageAccountCircle.setStroke(Color.TRANSPARENT);
        }
        agencyLoad = new AgencyLoad() {
            @Override
            public void reloadAgency() throws IOException {
                initialAdminAgency();
            }
        };

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
        initialAdminAgency();



    }

    private void initialAdminAgency() throws IOException {
        gridPaneAgency.getChildren().clear();
        staffListGridPane.getChildren().clear();

        processData = new ProcessData<>();
        staffListData = processData.getStaffList();
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/admin/navBarAdmin.fxml"));
        GridPane navbar = (GridPane) fxmlLoader.load();
        NavbarAdminController navbarAdminController = fxmlLoader.getController();
        navbarAdminController.setAdmin(account);
        root.add(navbar,0,0);
        int i = 1;

        for(Staff data: staffListData.getStaffList()) {
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/staff/staffList.fxml"));
            AnchorPane staffComponant = (AnchorPane) fxmlLoader.load();
            StaffListAgencyController staffListAgencyController = fxmlLoader.getController();
            staffListAgencyController.setData(data);
            GridPane.setMargin(staffComponant, new Insets(0,0,5,0));
            staffListGridPane.add(staffComponant,0,i);
            i++;
        }
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);

        i = 1;
        for(String temp : staffListData.getAgency() ) {
            Label label = new Label();
            label.setText(String.valueOf(i) + "."+temp);
            label.getStyleClass().add("textLabelColor");
            label.setFont(font);
            gridPaneAgency.add(label, 0, i);
            GridPane.setMargin(label, new Insets(0,0,10,0));
            i++;
        }

    }

    @FXML
    public void addAgencyButton() throws IOException {
        ApplicationController.goToNew("AdminAgencyAdd",agencyLoad);
    }
    @FXML
    public void addStuffButton() throws IOException {
        ApplicationController.goToNew("RegisterStuff");
    }
    @FXML
    public void handleAdminSettingButton(MouseEvent mouseEvent) {
        try {
            ApplicationController.goTo("Setting", account);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
