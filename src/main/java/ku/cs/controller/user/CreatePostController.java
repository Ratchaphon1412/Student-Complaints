package ku.cs.controller.user;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeInUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
import ku.cs.controller.components.navbar.NavbarUserController;
import ku.cs.models.report.Filterer;
import ku.cs.models.report.Report;
import ku.cs.models.report.ReportList;
import ku.cs.models.user.User;
import ku.cs.service.ProcessData;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
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
    private Circle imageAccountBigger;
    @FXML
    private Label roleDisplay;
    @FXML
    private Label displayNameBig;

    @FXML
    private Label roleDisplayBig;
    @FXML
    private ScrollPane accountfeed;


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
        //set Font
        Font font =  Font.loadFont(getClass().getResource("/ku/cs/assets/fonts/"+preferences.get("font",null)).toExternalForm(),15);
        displayName.setFont(font);
        roleDisplay.setFont(font);
        displayNameBig.setFont(font);
        roleDisplayBig.setFont(font);

        //getObject from router
        user = (User) ApplicationController.getData();
        initializePost();
    }
    private void initializePost() throws IOException {
        //load nav
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/components/user/navBarUser.fxml"));
        GridPane navbar = (GridPane) fxmlLoader.load();
        NavbarUserController navbarUser = fxmlLoader.getController();
        navbarUser.setUser(user);
        root.add(navbar, 0, 0);

        //set label
        displayName.setText(user.getUserName());
        roleDisplay.setText(user.getRole());
        displayNameBig.setText(user.getUserName());
        roleDisplayBig.setText(user.getRole());

        //set image
        File desDir = new File("image" + System.getProperty("file.separator") + "accounts" + System.getProperty("file.separator") + user.getPathPicture());
        Image image = new Image(String.valueOf(desDir.toURI()), 500, 0, true, true);

        if (!image.isError()) {
            imageAccountCircle.setFill(new ImagePattern(image));
            imageAccountCircle.setStroke(Color.TRANSPARENT);
            imageAccountBigger.setFill(new ImagePattern(image));
            imageAccountBigger.setStroke(Color.TRANSPARENT);
        }
        SwitchTheme changeTheme = new SwitchTheme() {
            @Override
            public void changeTheme(String theme) throws IOException {
                //save theme
                State state = new State();
                state.setTempData();
                state.saveThemeToConfig(theme);
                //change state
                Preferences preferences = Preferences.userRoot().node(State.class.getName());
                preferences.put("theme", theme);
                //change stylesheet in main page
                root.getStylesheets().clear();
                String styleTheme = "/ku/cs/style/" + preferences.get("theme", null) + ".css";
                String icon = "/ku/cs/style/icon.css";
                String style = "/ku/cs/style/style.css";
                root.getStylesheets().add(Objects.requireNonNull(getClass().getResource(styleTheme)).toExternalForm());
                root.getStylesheets().add(Objects.requireNonNull(getClass().getResource(icon)).toExternalForm());
                root.getStylesheets().add(Objects.requireNonNull(getClass().getResource(style)).toExternalForm());
            }
        };

        //Switch Theme
        FXMLLoader fxmlLoader1 = new FXMLLoader();
        fxmlLoader1.setLocation(getClass().getResource("/ku/cs/components/buttonTheme.fxml"));
        GridPane switchTheme = fxmlLoader1.load();
        ButtonThemeController buttonThemeController = fxmlLoader1.getController();
        buttonThemeController.setSwitchTheme(changeTheme);
        minisetting.add(switchTheme,1,1);
        refresh();
    }
    //load postuser
    public void refresh() throws IOException {
        GridPane gridScoll = new GridPane();

        accountfeed.setContent(gridScoll);
        accountfeed.setFitToWidth(true);
        int countRow = 0;
        ProcessData<User> processData = new ProcessData<>();
        ReportList reportList = processData.getReportList();
        List<Report> reports = reportList.getReportLists();
        reportList.setReportSetterSort(reports);
        ReportList tempReport = reportList.sortReport(new Filterer<Report>() {
            @Override
            public boolean filter(Report report) {
                if (user.getUserName().equals(report.getReporter().getUserName())){
                    return true;
                }
                return false;
            }
        });
        user.setReportList(tempReport.getReportSort());
        for (Report report: user.getReportList()) {
            FXMLLoader fxmlLoaderPost = new FXMLLoader();
            fxmlLoaderPost.setLocation(getClass().getResource("/ku/cs/components/user/userFeed.fxml"));
            GridPane postComponant = fxmlLoaderPost.load();
            ProblemFeedController problemFeedController = fxmlLoaderPost.getController();
            problemFeedController.setReport(report, user);
            GridPane.setMargin(postComponant, new Insets(0, 0, 15, 0));
            gridScoll.add(postComponant, 0, countRow);
            countRow++;
            System.out.println("Check");
        }

    }
    @FXML
    void addPostButton(ActionEvent event) throws IOException {
        Reposthable refreshable = new Reposthable() {
            @Override
            public void refreshPost() throws IOException {
                refresh();
            }
        };
        ApplicationController.goToNew("CreatePost",user, refreshable);
    }

    @FXML
    public void handleUserSettingButton() {
        try {
            ApplicationController.goTo("UserSetting",user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

