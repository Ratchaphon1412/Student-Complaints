package ku.cs.controller.admin;

import animatefx.animation.FadeIn;
import animatefx.animation.ZoomIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import ku.cs.ApplicationController;
import ku.cs.State;
import ku.cs.controller.Reposthable;
import ku.cs.controller.SwitchTheme;
import ku.cs.controller.components.ButtonThemeController;
import ku.cs.controller.components.admin.DeleteUserReportController;
import ku.cs.controller.components.navbar.NavbarAdminController;
import ku.cs.models.admin.Admin;
import ku.cs.models.report.Report;
import ku.cs.service.DataBase;
import ku.cs.service.ProcessData;


import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
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
    private Label titleCategory;

    @FXML
    private Label titleBigCategory;

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

//    @FXML
    private GridPane gridPaneCategory;

    @FXML
    private ScrollPane scroll;

    @FXML
    private ScrollPane scrollCategory;

    private FXMLLoader fxmlLoader;

    private ProcessData processData;

    private List<LinkedHashMap<String, String>> categoryList;
    private DataBase dataBase;

    private List<Report> requestBanPost;

    private BanAndUnBan banAndUnBan;

    private Reposthable reposthable;

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
        titleBigCategory.setFont(font);
        tableTitleAgency.setFont(font);
        titleReportAgency.setFont(font);
        titleCategory.setFont(font);
        tableTitleAgency.setFont(font);
        tableTitleRole.setFont(font);
        tableTitleUserName.setFont(font);
        displayName.setFont(font);
        roleDisplay.setFont(font);


        //scroll category

        gridPaneCategory = new GridPane();
        scrollCategory.setContent(gridPaneCategory);

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

        banAndUnBan = new BanAndUnBan() {
            @Override
            public void onClickBanOrUnban() throws IOException {
                initializeAdminCategory();
            }
        };


        initializeAdminCategory();

    }
    private void initializeAdminCategory() throws IOException{
        gridPaneCategory.getChildren().clear();
        processData = new ProcessData<>();
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
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/admin/navBarAdmin.fxml"));
        GridPane navbar = (GridPane) fxmlLoader.load();
        NavbarAdminController navbarAdminController = fxmlLoader.getController();
        navbarAdminController.setAdmin(account);
        root.add(navbar,0,0);

        //addList


        reposthable = new Reposthable() {
            @Override
            public void refreshPost() throws IOException {
                refresh();
            }
        };
        refresh();


        requestBanPost = processData.getReportList().getRequestDeleteReport();
        GridPane gridPane = new GridPane();
        gridPane.setPrefWidth(200);
        scroll.setFitToWidth(true);
        scroll.setContent(gridPane);

        int num = 0;
        for(Report report : requestBanPost){
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/admin/deletePostReport.fxml"));
            GridPane banPostUser = (GridPane) fxmlLoader.load();
            DeleteUserReportController deleteUserReportController = fxmlLoader.getController();
            deleteUserReportController.setData(report,account,banAndUnBan);

            gridPane.add(banPostUser,0,num++);
            GridPane.setMargin(banPostUser, new Insets(0,0,5,0));
        }




    }




    @FXML
    void goToAddCategoryButton(ActionEvent event) throws IOException {
        ApplicationController.goToNew("addCategory",reposthable);
    }

    @FXML
    public void handleAdminSettingButton(MouseEvent mouseEvent) {
        try {
            ApplicationController.goTo("Setting",account);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void refresh(){
        Preferences preferences = Preferences.userRoot().node(State.class.getName());
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);

        int i=1;

        processData = new ProcessData<>();
        dataBase = processData.getDataBase();
        categoryList = dataBase.getPatternList();
        for (LinkedHashMap<String, String> temp: categoryList){
            Label label = new Label();
//            label.setWrapText(true);
            label.setText(String.valueOf(i) + "."+temp.get("category")+ " - " + temp.get("agency"));
            label.getStyleClass().add("textLabelColor");
            label.setFont(font);
//            label.setMinWidth(Region.USE_COMPUTED_SIZE);
            gridPaneCategory.add(label, 0, i);
            GridPane.setMargin(label, new Insets(0,0,10,0));
            i++;
        }
    }


}
